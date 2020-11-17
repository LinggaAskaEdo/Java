package com.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig
{
    @Bean
    Queue marketingQueue()
    {
        return new Queue("marketingQueue", true);
    }

    @Bean
    Queue financeQueue()
    {
        return new Queue("financeQueue", true);
    }

    @Bean
    Queue adminQueue()
    {
        return new Queue("adminQueue", true);
    }

    @Bean
    DirectExchange directExchange()
    {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    Binding marketingBinding(Queue marketingQueue, DirectExchange directExchange)
    {
        return BindingBuilder.bind(marketingQueue).to(directExchange).with("marketing");
    }

    @Bean
    Binding financeBinding(Queue financeQueue, DirectExchange directExchange)
    {
        return BindingBuilder.bind(financeQueue).to(directExchange).with("finance");
    }

    @Bean
    Binding adminBinding(Queue adminQueue, DirectExchange directExchange)
    {
        return BindingBuilder.bind(adminQueue).to(directExchange).with("admin");
    }

    @Bean
    Queue carQueue()
    {
        return new Queue("carQueue", true);
    }

    @Bean
    DirectExchange carExchange()
    {
        return new DirectExchange("car-exchange");
    }

    @Bean
    Binding carBinding(Queue carQueue, DirectExchange carExchange)
    {
        return BindingBuilder.bind(carQueue).to(carExchange).with("car");
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}