package com.sql2o.hexagonal.adapter.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration
{
//    private static final Logger logger = LogManager.getLogger();

//    private final ConfigPreference preference;

//    @Autowired
//    public RabbitConfiguration(ConfigPreference preference)
//    {
//        this.preference = preference;
//    }

//    @Bean
//    public Queue carQueue()
//    {
//        return new Queue("carQueue", false);
//    }
//
//    @Bean
//    public DirectExchange carExchange()
//    {
//        return new DirectExchange("car-exchange");
//    }
//
//    @Bean
//    public TopicExchange topicExchange()
//    {
//        return new TopicExchange("zzz");
//    }
//
//    @Bean
//    public Binding directBinding(DirectExchange carExchange, Queue carQueue)
//    {
//        return BindingBuilder.bind(carQueue).to(carExchange).with("car");
//    }
//
//    @Bean
//    public Binding topicBinding(TopicExchange topicExchange, Queue queue)
//    {
//        return BindingBuilder.bind(queue).to(topicExchange).with("xxx");
//    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public ConnectionFactory connectionFactory()
//    {
//        CachingConnectionFactory factory = new CachingConnectionFactory();
//        factory.setHost(preference.rabbitmqHost);
//        factory.setPort(preference.rabbitmqPort);
//        factory.setUsername(preference.rabbitmqUsername);
//        factory.setPassword(preference.rabbitmqPassword);
//
//        return factory;
//    }

//    @Bean
//    public AmqpTemplate template()
//    {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//
//        return rabbitTemplate;
//    }

//    @Bean
//    public SimpleMessageListenerContainer listenerContainer()
//    {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueName(this.helloWorldQueueName);
//        container.setMessageListener(new MessageListenerAdapter(new HelloWorldHandler()));
//
//        return container;
//    }
}