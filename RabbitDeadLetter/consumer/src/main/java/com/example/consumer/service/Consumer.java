package com.example.consumer.service;

import com.example.consumer.exception.InsufficientFundsException;
import com.example.consumer.model.PaymentOrder;
import com.example.consumer.preference.ConstantPreference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class Consumer
{
    private static final Logger logger = LogManager.getLogger();

    @RabbitListener(queues = ConstantPreference.INCOMING_QUEUE_NAME)
    public void process(@Payload PaymentOrder paymentOrder) throws InsufficientFundsException
    {
        logger.info("Processing at '{}' payload '{}'", new Date(), paymentOrder);

        if (new Random().nextBoolean())
        {
            String message = "insufficient funds on account " + paymentOrder.getFrom();

            logger.error(message);

            throw new InsufficientFundsException(message);
        }
    }
}