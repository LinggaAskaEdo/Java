package com.example.service.queues.controller;

import com.example.service.queues.model.PaymentOrder;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iban4j.Iban;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@RestController
@Service
public class ServiceQueuesController
{
    private static final Logger logger = LogManager.getLogger();

    private final RabbitTemplate rabbitTemplate;
    private final Random random;

    public ServiceQueuesController(RabbitTemplate rabbitTemplate) throws NoSuchAlgorithmException
    {
        this.rabbitTemplate = rabbitTemplate;
        this.random = SecureRandom.getInstanceStrong();
    }

    @PostMapping(value = "/v1/payment")
    public void registration(@RequestBody PaymentOrder paymentOrder)
    {
        paymentOrder.validate();

        logger.debug("request: {}", new Gson().toJson(paymentOrder));

        rabbitTemplate.convertAndSend("payment-orders.exchange", "payment-orders.work.routing-key", paymentOrder);
    }

    @Scheduled(fixedDelay = 5000L)
    public void send()
    {
        PaymentOrder paymentOrder = new PaymentOrder(Iban.random().toFormattedString(), Iban.random().toFormattedString(), generateRandomNumber());

        logger.info("Sending payload '{}'", new Gson().toJson(paymentOrder));

        rabbitTemplate.convertAndSend("payment-orders.exchange", "payment-orders.work.routing-key", paymentOrder);
    }

    private int generateRandomNumber()
    {
        int low = 10;
        int high = 100;

        return random.nextInt(high - low) + low;
    }
}