package rabbit;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by adi on 1/19/18.
 */
public class MyProcessor implements Processor {

    public static String MSG_PREFIX_JOIN = "join-";
    public static String MSG_RESPONSE_JOIN_OK = "join-ok";
    public static String MSG_PREFIX_REBUILD = "rebuild-";
    public static String MSG_RESPONSE_REBUILD_OK = "rebuild-ok";
    public static String MSG_PREFIX_CHECK = "check-";
    public static String MSG_RESPONSE_CHECK_OK = "check-ok";
    public static String MSG_PREFIX_START_REBUILD = "start-rebuild-";
    public static String MSG_RESPONSE_START_REBUILD_OK = "start-rebuild-ok";
    public static String MSG_PREFIX_CONTINUE_REBUILD = "continue-rebuild-";
    public static String MSG_RESPONSE_CONTINUE_REBUILD_OK = "continue-rebuild-ok";
    public static String MSG_PREFIX_FINISH_REBUILD = "finish-rebuild-";
    public static String MSG_RESPONSE_FINISH_REBUILD_OK = "finish-rebuild-ok";
    public static String MSG_PREFIX_CANCEL_REBUILD = "cancel-rebuild-";
    public static String MSG_RESPONSE_CANCEL_REBUILD_OK = "cancel-rebuild-ok";

    public static String MSG_RESPONSE_ERROR = "ERROR";

    private final long rebuild_start_wait_interval_ms = 100;


    private final Util util = new Util();
    private final ConsumerManager consumerManager;

    public MyProcessor(ConsumerManager consumerManager){
        this.consumerManager = consumerManager;
    }

    @Override
    public String process(String msg, ChannelHandlerContext ctx) {
        String response = MSG_RESPONSE_ERROR;

//        System.out.println("In MyProcessor GOT:"+msg);

        if(msg == null){
            return response;
        }

        if(msg.startsWith(MSG_PREFIX_CHECK)){

            response = MSG_RESPONSE_CHECK_OK;

        }else if(msg.startsWith(MSG_PREFIX_JOIN)){
            if(msg.length() > MSG_PREFIX_JOIN.length()) {
                String ownHostPortString = consumerManager.getOwnHost() + ":" + consumerManager.getOwnPort();
                String hostPortString = msg.substring(MSG_PREFIX_JOIN.length());
                if(hostPortString != null && hostPortString.split(":").length >= 2 && !ownHostPortString.equals(hostPortString)){
                    //Check if it is not from itself
                    String[] hostPort = hostPortString.split(":");
                    try {
                        String socketResponse = util.socketCall(hostPort[0], Integer.parseInt(hostPort[1]), MSG_PREFIX_CHECK, Util.SOCKET_DEFAULT_TIMEOUT_MS);
                        if(MSG_RESPONSE_CHECK_OK.equals(socketResponse)){
                            if(consumerManager.otherCustomerJoin(hostPortString)) {
                                response = MSG_RESPONSE_JOIN_OK;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }else if(msg.startsWith(MSG_PREFIX_REBUILD)){
            if(msg.length() > MSG_PREFIX_REBUILD.length()) {
                String ownHostPortString = consumerManager.getOwnHost() + ":" + consumerManager.getOwnPort();
                String hostPortString = msg.substring(MSG_PREFIX_REBUILD.length());
                if(hostPortString != null && hostPortString.split(":").length >= 2 && !ownHostPortString.equals(hostPortString)) {
                    System.out.println("REBUILD-called:"+hostPortString);
                    String rebuildStatus = Rebuilder.rebuild(consumerManager.getOwnHost(), consumerManager.getOwnPort(), hostPortString);
                    if(Rebuilder.STATUS_REBUILD_OK.equals(rebuildStatus)) {
                        response = MSG_RESPONSE_REBUILD_OK;
                    }
                }
            }
        }else if(msg.startsWith(MSG_PREFIX_START_REBUILD)) {
            if (msg.length() > MSG_PREFIX_START_REBUILD.length()) {
                String consumerCheckList = msg.substring(MSG_PREFIX_START_REBUILD.length());

                Rebuilder rebuilder = Rebuilder.rebuilder.get();
                if(rebuilder != null){
                    while (!rebuilder.isStarted()){
                        try {
                            Thread.sleep(rebuild_start_wait_interval_ms);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if(!rebuilder.isCancel()){
                        if(rebuilder.getConsumerCheck().toString().equals(consumerCheckList)){
                            response = MSG_RESPONSE_START_REBUILD_OK;
                        }
                    }
                }
            }
        }else if(msg.startsWith(MSG_PREFIX_CONTINUE_REBUILD)) {
//            if (msg.length() > MSG_PREFIX_CONTINUE_REBUILD.length()) {
                Rebuilder rebuilder = Rebuilder.rebuilder.get();
                if(rebuilder != null && rebuilder.isStarted() && !rebuilder.isCancel()){
//                    rebuilder.isRebuildContinue.set(true);
                    rebuilder.continueRebuild();
                    response = MSG_RESPONSE_CONTINUE_REBUILD_OK;
                }
//            }
        }else if(msg.startsWith(MSG_PREFIX_FINISH_REBUILD)) {
            if (msg.length() > MSG_PREFIX_FINISH_REBUILD.length()) {
                String hostPortString = msg.substring(MSG_PREFIX_FINISH_REBUILD.length());
                Rebuilder rebuilder = Rebuilder.rebuilder.get();
                if(rebuilder != null && rebuilder.isStarted()){
                    rebuilder.removeConsumerCheck(hostPortString);
                    response = MSG_RESPONSE_FINISH_REBUILD_OK;
                }
            }
        }
        else if(msg.startsWith(MSG_PREFIX_CANCEL_REBUILD)) {
            Rebuilder rebuilder = Rebuilder.rebuilder.get();
            if(rebuilder != null && !rebuilder.isCancel()){
                rebuilder.cancel();
                response = MSG_RESPONSE_CANCEL_REBUILD_OK;
            }
        }

        System.out.println("Socket IN<<:"+msg+"||Socket OUT>>:"+response);

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return response;
    }
}
