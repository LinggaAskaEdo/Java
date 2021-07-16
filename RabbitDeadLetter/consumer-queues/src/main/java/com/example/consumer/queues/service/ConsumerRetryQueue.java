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
public class ConsumerRetryQueue
{
    private static final Logger logger = LogManager.getLogger();

    private final ConsumerUtil util;

    @Autowired
    public ConsumerRetryQueue(ConsumerUtil util)
    {
        this.util = util;
    }

    @RabbitListener(queues = "${payment-orders.retry.queue}")
    public void retryProcess(@Payload PaymentOrder paymentOrder, Message message)
    {
        int retry = util.countRetry(message);
        String strPayload = new Gson().toJson(paymentOrder);

        try
        {
            if (util.isRetryAvailable(message))
            {
//                int result = util.generateRandomNumber();

//                logger.debug("result: {}", result);

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
            else
            {
                util.reRetryQueue(message);
            }
        }
        catch (Exception e)
        {
            logger.error("Error retry queue", e);
            util.reRetryQueue(message);
        }
    }
}
