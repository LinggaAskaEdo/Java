package rabbit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by adi on 1/22/18.
 */
public class ConsumerManager {

    private static ConsumerManager instance;

    private final Util util = new Util();
    private final Connection conn;

    private final String userName;
    private final String password;
    private final String ownHost;
    private final int ownPort;

    private final String apiUrl;
    private final String queueName;
    private final int consumerCount;
    private final int consumerWeight;
    private final boolean autoAck;
    private final String consumerTagPrefix;
    private final int prefetchCount;
    private final String consumerId;
    private final int mgmtPort;

    private final AtomicBoolean isRebuildOnJoinCancelled = new AtomicBoolean(false);

    public String getOwnHost() {
        return ownHost;
    }

    public int getOwnPort() {
        return ownPort;
    }

    public boolean isRebuildOnJoinCancelled() {
        return isRebuildOnJoinCancelled.get();
    }

    public void resumeCancelledRebuildOnJoin(){
        isRebuildOnJoinCancelled.set(false);
    }

    public Map<String, String> getOtherConsumers() {
        updateConsumerDetails(getCurrentConsumerDetails(), false, false);
        return new HashMap<>(otherConsumers);
    }

    private List<MyConsumer> thisConsumers = Collections.synchronizedList(new ArrayList<>());
    private Map<String, String> otherConsumers = new ConcurrentHashMap<>();

    public static ConsumerManager getStaticInstance(){
        return instance;
    }

    public ConsumerManager(Connection conn, String userName, String password, String ownHost, int ownPort, String apiUrl, String queueName, int consumerCount, int consumerWeight, boolean autoAck
            , String consumerTagPrefix, int prefetchCount, String consumerId, int mgmtPort){
        this.conn = conn;
        this.userName = userName;
        this.password = password;
        this.ownHost = ownHost;
        this.ownPort = ownPort;
        this.apiUrl = apiUrl;
        this.queueName = queueName;
        this.consumerCount = consumerCount;
        this.consumerWeight = consumerWeight;
        this.autoAck = autoAck;
        this.consumerTagPrefix = consumerTagPrefix;
        this.prefetchCount = prefetchCount;
        this.consumerId = consumerId;
        this.mgmtPort = mgmtPort;
        ConsumerManager.instance = this;
    }

    public int rebuildConsumer(int rebuildedConsumerTotal) {
        int rebuildedConsumer = -1; //negative value means error

        int consumerTobeCreated = consumerWeight;
        int remainingConsumerTobeRebuilded = consumerCount - rebuildedConsumerTotal;

        if(remainingConsumerTobeRebuilded < consumerTobeCreated){
            consumerTobeCreated = remainingConsumerTobeRebuilded;
        }

        if(consumerTobeCreated <= 0){
            rebuildedConsumer = 0;
        }else {
            rebuildedConsumer = 0;

            for (int i = 0; i < consumerTobeCreated; i++) {
                try {
                    //TODO what if consumer creation failed??
                    if(createConsumer()) {
                        rebuildedConsumer++;
                        MyConsumer firstUncancelledConsumer = MyConsumer.findFirstUncancelled(thisConsumers);
                        if(firstUncancelledConsumer != null ){
                            firstUncancelledConsumer.cancelConsumer();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("rebuildedConsumer:"+rebuildedConsumer);
        return rebuildedConsumer;
    }

    public static AtomicInteger priority = new AtomicInteger(1);

    public boolean createConsumer() throws IOException, TimeoutException {
        boolean isSuccess = false;

        String uuid = UUID.randomUUID().toString();
        String consumerTag = consumerTagPrefix + "#" +  uuid;

        Channel channel = conn.createChannel();
        channel.basicQos(prefetchCount);

        final CountDownLatch consumeOkCountDownLatch = new CountDownLatch(1);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {



                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();

//                System.out.println(consumerTag + ":Got:" + new String(body));

                try {
                    Files.write(Paths.get("/app/logs/racingConsumerTest/racingConsumer.log"), (mgmtPort + "-" + consumerTag+ ":Got:" + new String(body) + "\n").getBytes(), StandardOpenOption.APPEND);
                }catch (NoSuchFileException e) {
                    Files.write(Paths.get("/app/logs/racingConsumerTest/racingConsumer.log"), (mgmtPort + "-" + consumerTag+ ":Got:" + new String(body) + "\n").getBytes(), StandardOpenOption.CREATE);
                }

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if(!autoAck) {
                    channel.basicAck(deliveryTag, false);
                }
//                System.out.println("acked");
            }

            @Override
            public void handleCancel(String consumerTag) throws IOException {
                //Invoked when the queue is deleted
                System.out.println("handleCancel:"+consumerTag);
                super.handleCancel(consumerTag);
                thisConsumers.remove(new MyConsumer(consumerTag));
                try {
                    channel.close();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void handleCancelOk(String consumerTag) {
                //Invoked when channel.basicCancel()
                System.out.println("handleCancelOk:"+consumerTag);
                super.handleCancelOk(consumerTag);
                thisConsumers.remove(new MyConsumer(consumerTag));
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void handleRecoverOk(String consumerTag) {
                System.out.println("handleRecoverOk:"+consumerTag);
                super.handleRecoverOk(consumerTag);
            }

            @Override
            public void handleConsumeOk(String consumerTag) {
                System.out.println("handleConsumeOk:"+consumerTag);
                super.handleConsumeOk(consumerTag);
                consumeOkCountDownLatch.countDown();
            }

        };

        Map<String, Object> args = new HashMap<String, Object>();
        args.put("v3ConsumerMgmtPort", mgmtPort);
        args.put("v3ConsumerId", consumerId);
//        args.put("x-priority", priority.get());
        String consumerTagReturn = channel.basicConsume(queueName, autoAck, consumerTag, false, false, args, defaultConsumer);

        try {
            consumeOkCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(consumerTagReturn != null && consumerTagReturn.equals(consumerTag)){
            thisConsumers.add(new MyConsumer(consumerTag, defaultConsumer, channel));
            isSuccess = true;
        }else{
            System.out.println("Failed creating consumer with tag:"+consumerTag);
        }

        return isSuccess;
    }

    public Queue getQueueDetails(String urlString, String userName, String password, String queueName){
        Queue queue = null;
        if(queueName == null){
            return queue;
        }

        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlString + "/" + queueName + "?columns=consumer_details");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            String userpass = userName + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            con.setRequestProperty ("Authorization", basicAuth);

            inputStream = con.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                sb.append(line + "\n");
            }
            String resultString = sb.toString();

//            System.out.println(resultString);

            if(resultString!=null && !resultString.isEmpty()){
                queue = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(resultString, Queue.class);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return queue;
    }

    public Map<String, String> getCurrentConsumerDetails(){

        Queue queueDetails = getQueueDetails(apiUrl, userName, password, queueName);

        Map<String, String> otherConsumers = new HashMap<>();

        if(queueDetails != null && queueDetails.getConsumer_details() != null){

            for(ConsumerDetails consumerDetails : queueDetails.getConsumer_details()){

                String consumer_tag = consumerDetails.getConsumer_tag();
                if(consumer_tag != null && consumer_tag.startsWith(RacingConsumerTest.v3ConsumerTagPrefix)
                        && !thisConsumers.contains(new MyConsumer(consumer_tag))) {

                    String peer_host = consumerDetails.getChannel_details().getPeer_host();
                    String v3ConsumerId = consumerDetails.getArguments().getV3ConsumerId();
                    int v3ConsumerMgmtPort = consumerDetails.getArguments().getV3ConsumerMgmtPort();

                    otherConsumers.put(peer_host+":"+v3ConsumerMgmtPort, v3ConsumerId);
                }

            }

        }

        return otherConsumers;
    }

    public void updateConsumerDetails(Map<String, String> otherConsumers, boolean isJoin, boolean isJoinOnlyNewConsumer){
        synchronized (this.otherConsumers) {
            boolean doRebuild = false;
            //First remove non-existing customers
            this.otherConsumers.keySet().retainAll(otherConsumers.keySet());

            for (Map.Entry<String, String> entry : otherConsumers.entrySet()) {

                boolean isNewOtherConsumer = false;

                if (!this.otherConsumers.containsKey(entry.getKey())) {
                    String[] hostAndPort = entry.getKey().split(":");
                    if (hostAndPort.length >= 2) {
                        this.otherConsumers.put(entry.getKey(), entry.getValue());
                        isNewOtherConsumer = true;
                    }
                }

                if (isJoin && (!isJoinOnlyNewConsumer || (isJoinOnlyNewConsumer && isNewOtherConsumer))) {
                    String[] hostAndPort = entry.getKey().split(":");
                    if (hostAndPort.length >= 2) {
                        doRebuild = true;
                        String host = hostAndPort[0];
                        int port = Integer.parseInt(hostAndPort[1]);
                        try {
                            String joinReponse = util.socketCall(host, port, MyProcessor.MSG_PREFIX_JOIN + ownHost + ":" + ownPort, Util.SOCKET_DEFAULT_TIMEOUT_MS);
                            if (MyProcessor.MSG_RESPONSE_JOIN_OK.equals(joinReponse)) {
//                            socketReponse = util.socketCall(host, port, MyProcessor.MSG_PREFIX_REBUILD + ownHost + ":" + ownPort, Util.SOCKET_DEFAULT_TIMEOUT_MS);
                                doRebuild = true;
                            }else{
                                String cancelRebuildReponse = util.socketCall(host, port, MyProcessor.MSG_PREFIX_CANCEL_REBUILD, Util.SOCKET_DEFAULT_TIMEOUT_MS);
                                doRebuild = false;
                                isRebuildOnJoinCancelled.set(true);
//                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
          if(doRebuild) {
              Rebuilder.rebuild(ownHost, ownPort, null);
          }
        }
    }

    public synchronized boolean otherCustomerJoin(String hostAndPort){
        String[] hostAndPortArr = hostAndPort.split(":");
        if (hostAndPortArr.length >= 2) {
            this.otherConsumers.put(hostAndPort, "TODO");

            Rebuilder rebuilder = Rebuilder.rebuilder.get();
            return rebuilder == null || (!rebuilder.isStarted() && !rebuilder.isCancel());
        }else{
            return false;
        }
    }



}
