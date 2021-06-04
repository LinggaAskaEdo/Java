package com.example.consumer.queues.config;

import com.example.consumer.queues.preference.ConfigPreference;
import com.example.consumer.queues.preference.ConstantPreference;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

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
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer)
    {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPrefetchCount(5);
        factory.setChannelTransacted(true);
        factory.setDefaultRequeueRejected(false);
        factory.setConcurrentConsumers(5);
        factory.setMaxConcurrentConsumers(10);

        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(250);
        backOffPolicy.setMultiplier(2.0);
        backOffPolicy.setMaxInterval(5000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        factory.setRetryTemplate(retryTemplate);

        configurer.configure(factory, connectionFactory());

        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate()
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin()
    {
        return new RabbitAdmin(connectionFactory());
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar)
    {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(defaultMessageHandlerMethodFactory);
    }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(configPreference.rabbitExchangeName, true, false);
    }

    @Bean
    public Queue workQueue()
    {
        return QueueBuilder
                .durable(configPreference.workQueue)
                .withArgument(ConstantPreference.X_DEAD_LETTER_EXCHANGE, configPreference.rabbitExchangeName)
                .withArgument(ConstantPreference.X_DEAD_LETTER_ROUTING_KEY, configPreference.waitRoutingKey)
                .build();
    }

    @Bean
    public Binding bindingWorkQueue()
    {
        return BindingBuilder
                .bind(workQueue())
                .to(directExchange())
                .with(configPreference.workRoutingKey);
    }

    @Bean
    public Queue waitQueue()
    {
        return QueueBuilder.durable(configPreference.waitQueue)
                .withArgument(ConstantPreference.X_DEAD_LETTER_EXCHANGE, configPreference.rabbitExchangeName)
                .withArgument(ConstantPreference.X_DEAD_LETTER_ROUTING_KEY, configPreference.retryRoutingKey)
                .withArgument(ConstantPreference.X_MESSAGE_TTL, configPreference.rabbitWaitTtl)
                .build();
    }

    @Bean
    public Binding bindingWaitQueue()
    {
        return BindingBuilder
                .bind(waitQueue())
                .to(directExchange())
                .with(configPreference.waitRoutingKey);
    }

    @Bean
    public Queue retryQueue()
    {
        return QueueBuilder
                .durable(configPreference.retryQueue)
                .withArgument(ConstantPreference.X_DEAD_LETTER_EXCHANGE, configPreference.rabbitExchangeName)
                .withArgument(ConstantPreference.X_DEAD_LETTER_ROUTING_KEY, configPreference.waitRoutingKey)
//                .withArgument(ConstantPreference.X_DEAD_LETTER_ROUTING_KEY, configPreference.parkRoutingKey)
                .build();
    }

    @Bean
    public Binding bindingRetryQueue()
    {
        return BindingBuilder
                .bind(retryQueue())
                .to(directExchange())
                .with(configPreference.retryRoutingKey);
    }

    @Bean
    public Queue parkQueue()
    {
        return QueueBuilder
                .durable(configPreference.parkQueue)
                .build();
    }

    @Bean
    public Binding bindingParkQueue()
    {
        return BindingBuilder
                .bind(parkQueue())
                .to(directExchange())
                .with(configPreference.parkRoutingKey);
    }
}