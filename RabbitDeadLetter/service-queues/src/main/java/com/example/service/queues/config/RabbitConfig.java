package com.example.service.queues.config;

import com.example.service.queues.preference.ConfigPreference;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@EnableRabbit
@Configuration
public class RabbitConfig implements RabbitListenerConfigurer
{
    private final ConfigPreference configPreference;

    private DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory;

    @Autowired
    public RabbitConfig(ConfigPreference configPreference)
    {
        this.configPreference = configPreference;
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory()
    {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(configPreference.rabbitHost);
        connectionFactory.setUsername(configPreference.rabbitUsername);
        connectionFactory.setPassword(configPreference.rabbitPassword);
        connectionFactory.setPort(configPreference.rabbitPort);

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate()
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar)
    {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(defaultMessageHandlerMethodFactory);
    }
}