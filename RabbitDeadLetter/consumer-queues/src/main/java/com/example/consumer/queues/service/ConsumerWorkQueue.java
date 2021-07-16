package com.example.consumer.queues.service;

import com.example.consumer.queues.model.PaymentOrder;
import com.example.consumer.queues.util.ConsumerUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerWorkQueue
{
    private static final Logger logger = LogManager.getLogger();

    private final ConsumerUtil util;

    @Autowired
    public ConsumerWorkQueue(ConsumerUtil util)
    {
        this.util = util;
    }

    @RabbitListener(queues = "${payment-orders.work.queue}", containerFactory = "listenerContainerFactory")
    public void workProcess(@Payload PaymentOrder paymentOrder, Message message)
    {
        int retry = util.countRetry(message);
        String strPayload = new Gson().toJson(paymentOrder);

        try
        {
//            int result = util.generateRandomNumber();

//            logger.debug("result: {}", result);

//            if (paymentOrder.getAmount() < result)
            if (paymentOrder.getAmount() > 50)
            {
                logger.debug("Process data: {}, retry: {}", strPayload, retry);
            }
            else
            {
                logger.debug("ReQueue data: {}, retry: {}", strPayload, retry);

                util.reRetryQueue(message);
            }
        }
        catch (Exception e)
        {
            logger.error("Error work queue", e);
            util.reRetryQueue(message);
        }
    }
}