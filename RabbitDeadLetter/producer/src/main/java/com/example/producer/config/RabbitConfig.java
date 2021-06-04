package com.example.producer.config;

import com.example.producer.preference.ConstantPreference;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig
{
    @Bean
    DirectExchange exchange()
    {
        return new DirectExchange(ConstantPreference.EXCHANGE_NAME);
    }

    @Bean
    Queue incomingQueue()
    {
        return QueueBuilder.durable(ConstantPreference.INCOMING_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", ConstantPreference.DEAD_LETTER_QUEUE_NAME)
                .build();
    }

    @Bean
    Binding binding()
    {
        return BindingBuilder.bind(incomingQueue()).to(exchange()).with(ConstantPreference.ROUTING_KEY_NAME);
    }

    @Bean
    Queue deadLetterQueue()
    {
        return QueueBuilder.durable(ConstantPreference.DEAD_LETTER_QUEUE_NAME).build();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}