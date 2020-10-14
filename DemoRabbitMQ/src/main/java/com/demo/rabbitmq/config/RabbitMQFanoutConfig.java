//package com.demo.rabbitmq.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQFanoutConfig
//{
//    @Bean
//    Queue marketingQueue()
//    {
//        return new Queue("marketingQueue", false);
//    }
//
//    @Bean
//    Queue financeQueue()
//    {
//        return new Queue("financeQueue", false);
//    }
//
//    @Bean
//    Queue adminQueue()
//    {
//        return new Queue("adminQueue", false);
//    }
//
//    @Bean
//    FanoutExchange exchange()
//    {
//        return new FanoutExchange("fanout-exchange");
//    }
//
//    @Bean
//    Binding marketingBinding(Queue marketingQueue, FanoutExchange exchange)
//    {
//        return BindingBuilder.bind(marketingQueue).to(exchange);
//    }
//
//    @Bean
//    Binding financeBinding(Queue financeQueue, FanoutExchange exchange)
//    {
//        return BindingBuilder.bind(financeQueue).to(exchange);
//    }
//
//    @Bean
//    Binding adminBinding(Queue adminQueue, FanoutExchange exchange)
//    {
//        return BindingBuilder.bind(adminQueue).to(exchange);
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter()
//    {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory)
//    {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//
//        return rabbitTemplate;
//    }
//}