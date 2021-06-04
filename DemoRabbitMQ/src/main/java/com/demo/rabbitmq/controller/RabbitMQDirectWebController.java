package com.demo.rabbitmq.controller;

import com.demo.rabbitmq.model.Car;
import com.demo.rabbitmq.model.Document;
import com.demo.rabbitmq.model.Registration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/direct")
public class RabbitMQDirectWebController
{
    private static final Logger logger = LogManager.getLogger();

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQDirectWebController(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey, @RequestParam("cuid") String cuid, @RequestParam("contractNumber") String contractNumber)
    {
        Document document = new Document(cuid, contractNumber);

        rabbitTemplate.convertAndSend(exchange, routingKey, document);

        return "Message sent to the RabbitMQ Successfully";
    }

    @GetMapping(value = "/car")
    public void car()
    {
        Car car = new Car(UUID.randomUUID());

        try
        {
            Registration registration = rabbitTemplate.convertSendAndReceiveAsType("car-exchange", "car", car, new ParameterizedTypeReference<Registration>() {});
            logger.debug(new ObjectMapper().writeValueAsString(registration));
        }
        catch (Exception e)
        {
            logger.error("Error when get carResponse: {}", e.getMessage());
        }
    }
}