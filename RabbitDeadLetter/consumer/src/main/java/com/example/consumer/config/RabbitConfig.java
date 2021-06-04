package com.example.consumer.config;

import com.example.consumer.preference.ConstantPreference;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig
{
    @Bean
    Queue incomingQueue()
    {
        return QueueBuilder.durable(ConstantPreference.INCOMING_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", ConstantPreference.DEAD_LETTER_QUEUE_NAME)
                .build();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}