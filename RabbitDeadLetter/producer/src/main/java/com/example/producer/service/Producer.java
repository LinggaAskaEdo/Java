package com.example.producer.service;

import com.example.producer.model.PaymentOrder;
import com.example.producer.preference.ConstantPreference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iban4j.Iban;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class Producer
{
    private static final Logger logger = LogManager.getLogger();
    private static final Random random = new Random();

    private final AmqpTemplate amqpTemplate;

    public Producer(AmqpTemplate amqpTemplate)
    {
        this.amqpTemplate = amqpTemplate;
    }

    @Scheduled(fixedDelay = 5000L)
    public void send()
    {
        PaymentOrder paymentOrder = new PaymentOrder(Iban.random().toFormattedString(), Iban.random().toFormattedString(), BigDecimal.valueOf(1D + random.nextDouble() * 100D).setScale(2, BigDecimal.ROUND_FLOOR));

        logger.info("Sending payload '{}'", paymentOrder);

        amqpTemplate.convertAndSend(ConstantPreference.EXCHANGE_NAME, ConstantPreference.ROUTING_KEY_NAME, paymentOrder);
    }
}