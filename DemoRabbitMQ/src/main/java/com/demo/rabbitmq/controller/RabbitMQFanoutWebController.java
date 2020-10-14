package com.demo.rabbitmq.controller;

import com.demo.rabbitmq.model.Document;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fanout")
public class RabbitMQFanoutWebController
{
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQFanoutWebController(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("cuid") String cuid, @RequestParam("contractNumber") String contractNumber)
    {
        Document document = new Document(cuid, contractNumber);

        rabbitTemplate.convertAndSend(exchange, "", document);

        return "Message sent to the RabbitMQ Successfully";
    }
}