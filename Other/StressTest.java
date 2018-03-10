package com.firstwap.altamides.v3.quota.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstwap.altamides.v3.commons.json.object.quota.QuotaResponseMessage;
import com.firstwap.altamides.v3.commons.json.object.router.RouterHeaderMsg;
import com.firstwap.altamides.v3.commons.json.object.router.RouterMsg;
import com.firstwap.altamides.v3.commons.json.object.router.RouterResponseMsg;
import com.firstwap.altamides.v3.commons.util.rabbitmq.rpcclient.RabbitMQRPCClient;
import com.firstwap.altamides.v3.commons.util.rabbitmq.rpcclient.RabbitMQRPCClientConnection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class StressTest
{
    private final String username;
    private final String password;
    private final String virtualHost;
    private final String[] rabbitAddresses;

    private RabbitMQRPCClientConnection rpcClientConn;

    private StressTest(String username, String password, String virtualHost, String[] rabbitAddresses) throws IOException, TimeoutException
    {
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
        this.rabbitAddresses = rabbitAddresses;
        System.out.println("Getting Connection...");
        this.rpcClientConn = getRabbitMQRPCClientConnection();
        System.out.println("Connection Established...");
    }

    private RabbitMQRPCClientConnection getRabbitMQRPCClientConnection() throws IOException, TimeoutException
    {
        String[] rabbitAddresses = this.rabbitAddresses;
        String username = this.username;
        String password = this.password;
        String virtualHost = this.virtualHost;
        long defaultTimeout = 1000 * 60;
        String defaultQueueTopic = "amq.topic";
        String defaultQueueRoutingKey = "ams.router";

        return new RabbitMQRPCClientConnection(rabbitAddresses, username, password, virtualHost, defaultTimeout, defaultQueueTopic, defaultQueueRoutingKey);
    }

    private Object getBodyGetQuota()
    {
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> paramBodyMap = new HashMap<>();

        paramBodyMap.put("userId", 1);
        //paramBodyMap.put("userId", 1);
        paramBodyMap.put("creditTypeId", 1);
        paramBodyMap.put("creditChange", -1);

        paramMap.put("action","GET");
        paramMap.put("body",paramBodyMap);

        return paramMap;
    }

    private RouterMsg getRequestQuota(Object body)
    {
        RouterMsg routerMsg = new RouterMsg();
        RouterHeaderMsg headerMsg = new RouterHeaderMsg();
        headerMsg.setTransactionId("TEST-123");
        headerMsg.setDestination("QuotaService");
        headerMsg.setResolvedDestination(false);
        headerMsg.setResolvedCallback(true);
        headerMsg.setSource("LINGGA-PC");
        routerMsg.setHeader(headerMsg);
        routerMsg.setBody(body);

        return routerMsg;
    }

    private QuotaResponseMessage sendToQS(RouterMsg msg)
    {
        try(RabbitMQRPCClient rpcClient = rpcClientConn.createClient(true))
        {
            String queueName = rpcClient.getDeclaredQueue();

            msg.getHeader().setCallback(","+queueName);

            QueueingConsumer.Delivery delivery = rpcClient.call(rpcClientConn.getDefaultQueueTopic(), rpcClientConn.getDefaultQueueRoutingKey(),msg.toJson(), true);

            QuotaResponseMessage quotaResponseMessage = null;

            if (delivery != null && delivery.getBody() != null)
            {
                RouterResponseMsg responseMsg = new ObjectMapper().readValue(delivery.getBody(), RouterResponseMsg.class);

                if (responseMsg.getBody()!=null && responseMsg.isStatus())
                {
                    quotaResponseMessage = new ObjectMapper().convertValue(responseMsg.getBody(), QuotaResponseMessage.class);
                }
            }

            return quotaResponseMessage;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException, TimeoutException
    {
        //long startTime = System.currentTimeMillis();

        final Date start = new Date();
        System.out.println(start);

        String username = "app";
        String password = "1rstwap";
        String virtualHost = "/";
        String[] rabbitAddresses = new String[] {args[0]+":5672"};

        final int loop = 1000;

        System.out.println("Starting...");

        final StressTest tester = new StressTest(username, password, virtualHost, rabbitAddresses);

        Runnable runnable = () ->
        {
            RouterMsg requestQuota;

            for (int j = 0; j < 1 ; j++)
            {
                System.out.println(Thread.currentThread().getName() + "-Loop-" + j);
                requestQuota = tester.getRequestQuota(tester.getBodyGetQuota());

                for (int i = 0; i < loop; i++)
                {
                    requestQuota.getHeader().setTransactionId(Thread.currentThread().getName() + "-TEST-123-" + i);
                    QuotaResponseMessage quotaResponseMessage = tester.sendToQS(requestQuota);

                    if (quotaResponseMessage != null)
                    {
                        Map<String, Object> body = (Map<String, Object>) quotaResponseMessage.getBody();
                        Date now = new Date();
                        System.out.println(new Date() + "[" + ((now.getTime() - start.getTime()) / 1000) + "]" + " " + Thread.currentThread().getName() + "-" + i + "-" + body.get("errorMessage"));
                    }
                }
            }
        };

        for (int k = 0; k < 10; k++)
        {
            Thread thread = new Thread(runnable);
            thread.start();

//            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//            service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
        }

        //long endTime   = System.currentTimeMillis();
        //long totalTime = endTime - startTime;

        //System.out.println("FINISH in " + totalTime + "(ms) !!!");
    }
}