package rabbit;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by adi on 1/22/18.
 */
public class Rebuilder {
    public static final AtomicReference<Rebuilder> rebuilder = new AtomicReference<>(null);
    public static final AtomicBoolean isLastRebuildCancelled = new AtomicBoolean(false);
    public static final AtomicReference<List<String>> lastRebuildList = new AtomicReference<List<String>>(new ArrayList<>());

    private final ConsumerManager consumerManager = ConsumerManager.getStaticInstance();
    private final List<String> consumerCheckA = new ArrayList<>();
    private final List<String> consumerCheckB = new ArrayList<>();
    private final List<String> consumerCheck = Collections.synchronizedList(new ArrayList<>());
    public final AtomicReference<CountDownLatch> continueRebuildLatch = new AtomicReference<>(new CountDownLatch(1));

    private final String ownHost;
    private final int ownPort;

    private Util util = new Util();
    private AtomicBoolean isCancel = new AtomicBoolean(false);
    private AtomicBoolean isStarted = new AtomicBoolean(false);
    private Date executionDate = new Date();
    private final long default_refresh_ms = 5000;
    private final long default_wait_interval_ms = 1000;
    private final long default_rebuild_continue_max_wait_ms = 15000;

    public static final String STATUS_REBUILD_OK = "OK";
    public static final String STATUS_REBUILD_ERROR = "ERROR";
    public static final String STATUS_REBUILD_ALREADY_STARTED = "ALREADY_STARTED";
    public static final String STATUS_REBUILD_ALREADY_CANCELLED = "ALREADY_CANCELLED";

    private Rebuilder(String ownHost, int ownPort){
        ConsumerManager.priority.set(ConsumerManager.priority.get() * -1);
        this.ownHost = ownHost;
        this.ownPort = ownPort;
        this.executionDate = new Date(new Date().getTime() + default_refresh_ms);
        isLastRebuildCancelled.set(false);
        initiate();
    }

    public boolean isStarted() {
        return isStarted.get();
    }

    public boolean isCancel() {
        return isCancel.get();
    }

    public List<String> getConsumerCheck() {
        return new ArrayList<>(consumerCheck);
    }

    public static synchronized String rebuild (String ownHost, int ownPort, String hostAndPortB) {
        String status = STATUS_REBUILD_ERROR;
        Rebuilder rebuilder = Rebuilder.rebuilder.get();

        if (rebuilder == null) {
            rebuilder = new Rebuilder(ownHost, ownPort);
            Rebuilder.rebuilder.set(rebuilder);
            status = STATUS_REBUILD_OK;
        } else if (rebuilder.isCancel()){
            status = STATUS_REBUILD_ALREADY_CANCELLED;
        } else if (rebuilder.isStarted()){
            rebuilder.cancel();
            status = STATUS_REBUILD_ALREADY_STARTED;
        } else {
            if(rebuilder.updateWaitingTime()) {
                status = STATUS_REBUILD_OK;
            }else{
                status = STATUS_REBUILD_ALREADY_STARTED;
            }
        }

        if (STATUS_REBUILD_OK.equals(status) && hostAndPortB != null) {
            rebuilder.updateListB(hostAndPortB);
        }

        return status;
    }

    public void removeConsumerCheck(String hostAndPort){
        if(hostAndPort != null) {
            consumerCheck.remove(hostAndPort);
        }
    }

    public void continueRebuild(){
        continueRebuildLatch.get().countDown();
    }

    public void resetContinueRebuildLatch(){
        final CountDownLatch oldContinueRebuildLatch = continueRebuildLatch.getAndSet(new CountDownLatch(1));
        while (oldContinueRebuildLatch.getCount() > 0){
            oldContinueRebuildLatch.countDown();
        }
    }

    private Date getExecutionDate(){
        synchronized (executionDate){
            return executionDate;
        }
    }

    private boolean updateExecutionDate(long ms){
        synchronized (executionDate){
            if(new Date().before(executionDate)) {
                executionDate = new Date(new Date().getTime() + ms);
                return true;
            }else{
                return false;
            }
        }
    }

    private void updateListA() {
        Map<String, String> otherConsumers = new HashMap<>(consumerManager.getOtherConsumers());
        synchronized (consumerCheckA) {
            consumerCheckA.retainAll(otherConsumers.keySet());
            String ownHostAndPort = ownHost + ":" + ownPort;
            if (!consumerCheckA.contains(ownHostAndPort)) {
                consumerCheckA.add(ownHostAndPort);
            }
            for (Map.Entry<String, String> entry : otherConsumers.entrySet()) {
                if (!consumerCheckA.contains(entry.getKey())) {
                    try {
                        String[] hostAndPortArr = entry.getKey().split(":");
                        System.out.println("SENDING rebuild trigger to:" + entry.getKey());
                        String socketReponse = util.socketCall(hostAndPortArr[0], Integer.parseInt(hostAndPortArr[1])
                                , MyProcessor.MSG_PREFIX_REBUILD + ownHost + ":" + ownPort, Util.SOCKET_DEFAULT_TIMEOUT_MS);
                        if (MyProcessor.MSG_RESPONSE_REBUILD_OK.equals(socketReponse)) {
                            consumerCheckA.add(entry.getKey());
                        } else {
                            cancel();
                            break;
                        }
                    } catch (Exception e) {
                        cancel();
                        System.out.println("error when triger rebuild:" + entry.getKey());
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
    }

    private void updateListB(String hostAndPort) {
        synchronized (consumerCheckB) {
            if (!consumerCheckB.contains(hostAndPort)) {
                consumerCheckB.add(hostAndPort);
            }
        }
    }

    private void initiate(){
        String ownHostAndPort = ownHost + ":" + ownPort;
        synchronized (consumerCheckA) {
            if(!consumerCheckA.contains(ownHostAndPort)) {
                consumerCheckA.add(ownHostAndPort);
            }
        }

        synchronized (consumerCheckB) {
            if(!consumerCheckB.contains(ownHostAndPort)) {
                consumerCheckB.add(ownHostAndPort);
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(new Date().before(getExecutionDate()) && !isCancel()) {
                        try {
                            Thread.sleep(default_wait_interval_ms);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        updateListA();
                        System.out.println(">>>>>>>>>>>after update list A,"+consumerCheckA);
                    }

                    if(isCancel()){
                        return;
                    }

                    isStarted.set(true);

                    System.out.println("REBUILD START");
                    System.out.println("listA"+consumerCheckA);
                    System.out.println("listB"+consumerCheckB);

                    consumerCheckA.retainAll(consumerCheckB);

                    if (consumerCheckA.isEmpty() || consumerCheckA.size() == 1) {
                        cancel();
                        return;
                    } else {
                        int currPosition = 0;
                        Collections.sort(consumerCheckA);
                        consumerCheck.addAll(consumerCheckA);

                        List<String> consumerCheckTemp = getConsumerCheck();
                        currPosition = consumerCheckTemp.indexOf(ownHostAndPort);
                        System.out.println("list to check:" + consumerCheckTemp);

                        for (String hostAndPort : consumerCheckTemp) {
                            if (!hostAndPort.equals(ownHostAndPort)) {
                                String[] hostAndPortArr = hostAndPort.split(":");
                                try {
                                    String response = util.socketCall(hostAndPortArr[0], Integer.parseInt(hostAndPortArr[1])
                                            , MyProcessor.MSG_PREFIX_START_REBUILD + consumerCheckTemp.toString(), Util.SOCKET_DEFAULT_TIMEOUT_MS);
                                    if (!MyProcessor.MSG_RESPONSE_START_REBUILD_OK.equals(response)) {
                                        cancel();
                                        return;
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        int totalRebuildedConsumer = 0;


                        String nextConsumerHostAndPort = ownHostAndPort;

                        if(currPosition == 0){
                            continueRebuild();
                        }

                        boolean isFinished = false;

                        while(isStarted() && !isCancel() && !isFinished){
                            Date rebuildContinueWaitStart = new Date();

                            try {
                                continueRebuildLatch.get().await(default_rebuild_continue_max_wait_ms, TimeUnit.MILLISECONDS);
                            } catch (InterruptedException e) {
                                System.out.println("Max wait for continue rebuild reached, cancelling own rebuild process and others.");
                                cancel();
                                for (String hostAndPort : getConsumerCheck()) {
                                    if (!ownHostAndPort.equals(hostAndPort)) {
                                        String[] hostAndPortArr = hostAndPort.split(":");
                                        String socketReponse = null;
                                        try {
                                            socketReponse = util.socketCall(hostAndPortArr[0], Integer.parseInt(hostAndPortArr[1])
                                                    , MyProcessor.MSG_PREFIX_CANCEL_REBUILD, Util.SOCKET_DEFAULT_TIMEOUT_MS);
                                        } catch (IOException ioe) {
                                            ioe.printStackTrace();
                                        }
                                    }
                                }
                                return;
                            }

                            resetContinueRebuildLatch();


                            //Always check nextConsumer, because we can skip it the next one if it already finish the rebuild consumer count.
                            nextConsumerHostAndPort = getNextConsumer(0);

                            System.out.println("NextConsumer:"+nextConsumerHostAndPort);

                            //TODO what if rebuild failed? rebuild 2 at once but all of it failed to be rebuild, retry or skip?
                            //TODO make it smarter
                            int rebuildedConsumer = consumerManager.rebuildConsumer(totalRebuildedConsumer);
                            if(rebuildedConsumer <= 0){
                                for (String hostAndPort : getConsumerCheck()) {
                                    if (!ownHostAndPort.equals(hostAndPort)) {
                                        String[] hostAndPortArr = hostAndPort.split(":");
                                        String socketReponse = null;
                                        try {
                                            socketReponse = util.socketCall(hostAndPortArr[0], Integer.parseInt(hostAndPortArr[1])
                                                    , MyProcessor.MSG_PREFIX_FINISH_REBUILD + ownHostAndPort, Util.SOCKET_DEFAULT_TIMEOUT_MS);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                isFinished = true;
                            }
                            totalRebuildedConsumer += rebuildedConsumer;

//                            Thread.sleep(1000);

                            int offset = 0;
                            while(!nextConsumerHostAndPort.equals(ownHostAndPort) && !isCancel()){
                                String[] hostAndPortArr = nextConsumerHostAndPort.split(":");
                                String socketReponse = null;
                                try {
                                    socketReponse = util.socketCall(hostAndPortArr[0], Integer.parseInt(hostAndPortArr[1])
                                            , MyProcessor.MSG_PREFIX_CONTINUE_REBUILD, Util.SOCKET_DEFAULT_TIMEOUT_MS);
                                    if(MyProcessor.MSG_RESPONSE_CONTINUE_REBUILD_OK.equals(socketReponse)){
                                        break;
                                    }else{
                                        System.out.println("skipped this next consumer:" + nextConsumerHostAndPort);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                nextConsumerHostAndPort = getNextConsumer(++offset);
                            }

                            if(nextConsumerHostAndPort.equals(ownHostAndPort)){
                                continueRebuild();
                            }
                        }
                    }
                } catch (Exception e){
                    System.out.println("Exception in builder thread");
                    e.printStackTrace();
                } finally {
                    if(isCancel()){
                        System.out.println("Rebuild cancelled");
                        isLastRebuildCancelled.set(true);
                    }else{
                        lastRebuildList.set(new ArrayList<>(consumerCheckA));
                    }
                    System.out.println("Finished");
                    rebuilder.set(null);
                }
            }
        }).start();

    }

    private String getNextConsumer(int offset){
        List<String> consumerCheckTemp = getConsumerCheck();
        int currPosition = consumerCheckTemp.indexOf(ownHost + ":" + ownPort) + offset;
        if(currPosition + 1 < consumerCheckTemp.size()) {
            return consumerCheckTemp.get(currPosition + 1);
        }else {
            return consumerCheckTemp.get(0);
        }
    }

    public void cancel(){
        isCancel.set(true);
    }

    public boolean updateWaitingTime(){
        if(isStarted.get()){
            cancel();
            return false;
        }else{
            return updateExecutionDate(default_refresh_ms);
        }
    }
}
