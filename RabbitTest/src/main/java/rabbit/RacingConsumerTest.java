package rabbit;

import com.rabbitmq.client.*;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * Created by adi on 1/19/18.
 */
public class RacingConsumerTest {

    private final ConnectionFactory factory;
    private final Connection conn;
    private final ConsumerManager consumerManager;
    private final Map<String, String> otherConsumers = new ConcurrentHashMap<>();
    private final Util util = new Util();

    private final String host;
    private final int port;
    private final String userName;
    private final String password;
    private final String virtualHost;
    private final String queueName;
    private final int consumerCount;
    private final int consumerWeight;
    private final int mgmtPort;

    private int rabbitMgmtPort = 15672;

    public static final String CONSUMER_MANAGER_JOB_DATA_KEY = "consumerManagerInstance";
    public static final String v3ConsumerTagPrefix = "V3ServiceConsumer";

    public RacingConsumerTest(String userName, String password, String virtualHost, String host, int port
            , String queueName, int consumerCount, int consumerWeight, boolean autoAck, String consumerTagPrefix, int prefetchCount, String consumerId, int mgmtPort) throws Exception {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.virtualHost = virtualHost;
        this.queueName = queueName;
        if(consumerCount < 1){
            throw new Exception("consumerCount should be at least 1");
        }
        this.consumerCount = consumerCount;
        if(consumerWeight < 1){
            throw new Exception("consumerWeight should be at least 1");
        }
        this.consumerWeight = consumerWeight;
        this.mgmtPort = mgmtPort;
        String apiUrl = "http://"+this.host+":"+rabbitMgmtPort+"/api/queues/"+URLEncoder.encode(this.virtualHost, "UTF-8");

        factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(host);
        factory.setPort(port);
        factory.setRequestedChannelMax((consumerCount * 3) + 10); // add buffer for rebuilding
        factory.setAutomaticRecoveryEnabled(true);
        conn = factory.newConnection();
        Recoverable recoverable = (Recoverable)conn;
        recoverable.addRecoveryListener(new RecoveryListener() {
            @Override
            public void handleRecovery(Recoverable recoverable) {
                System.out.println("Recovered!!!");

                if(consumerManager != null){
                    Map<String, String> otherConsumers = consumerManager.getCurrentConsumerDetails();
                    consumerManager.updateConsumerDetails(otherConsumers, true, false);
                }
            }
        });
        consumerManager = new ConsumerManager(conn, userName, password, InetAddress.getLocalHost().getHostAddress(), mgmtPort, apiUrl, queueName
                , consumerCount, consumerWeight, autoAck, consumerTagPrefix, prefetchCount, consumerId, mgmtPort);
    }

    public void initiateCustomer(){
        try {
            for (int i = 0 ; i < this.consumerCount ; i++) {
                consumerManager.createConsumer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void initiate() throws UnknownHostException {

        MyProcessor myProcessor = new MyProcessor(consumerManager);
        CountDownLatch consumeMgmtServerLatch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ConsumerManagementServer consumerManagementServer = new ConsumerManagementServer(mgmtPort, myProcessor, consumeMgmtServerLatch);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            consumeMgmtServerLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initiateCustomer();

        Map<String, String> otherConsumers = consumerManager.getCurrentConsumerDetails();
        consumerManager.updateConsumerDetails(otherConsumers, true, false);

        System.out.println("starting scheduler");
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = null;

        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(CONSUMER_MANAGER_JOB_DATA_KEY, consumerManager);

            scheduler = factory.getScheduler();

            JobDetail job = JobBuilder.newJob().storeDurably()
                    .withIdentity("ConsumerManagerJob").usingJobData(jobDataMap)
                    .ofType(RacingConsumerJob.class).build();

            Trigger trigger = TriggerBuilder.newTrigger().forJob(job)
                    .withSchedule(cronSchedule("0/10 * * * * ?")).build();

            scheduler.addJob(job, false);
            scheduler.scheduleJob(trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println("starting scheduler ends");
    }

    public static void main(String[] args) throws UnknownHostException {

        CountDownLatch a = new CountDownLatch(1);
        try {
            a.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String userName = "app";
        String password = "1rstwap";
        String host = "10.32.6.20";
        int port = 5672;
        String virtualHost = "/";
        String queueName = "queueA";
        int consumerCount = 5;
        int consumerWeight = Integer.parseInt(args[1]);
        boolean autoAck = false;
        int prefetchCount = 1;
        String consumerId = "Node1";
        int mgmtPort = Integer.parseInt(args[0]);

        RacingConsumerTest racingConsumerTest = null;
        try {
            racingConsumerTest = new RacingConsumerTest(userName, password, virtualHost, host, port
                    , queueName, consumerCount, consumerWeight, autoAck, v3ConsumerTagPrefix, prefetchCount, consumerId, mgmtPort);
            racingConsumerTest.initiate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
