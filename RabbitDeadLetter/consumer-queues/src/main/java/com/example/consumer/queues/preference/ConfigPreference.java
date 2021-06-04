package com.example.consumer.queues.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigPreference
{
    @Value("${spring.rabbitmq.host}")
    public String rabbitHost;

    @Value("${spring.rabbitmq.username}")
    public String rabbitUsername;

    @Value("${spring.rabbitmq.password}")
    public String rabbitPassword;

    @Value("${spring.rabbitmq.port}")
    public int rabbitPort;

    @Value("${spring.rabbitmq.channel-cache-size}")
    public int rabbitCacheSize;

    @Value("${spring.rabbitmq.concurrent-consumers}")
    public int rabbitConcurrentConsumers;

    @Value("${spring.rabbitmq.max-concurrent-consumers}")
    public int rabbitMaxConcurrentConsumers;

    @Value("${spring.rabbitmq.wait-ttl}")
    public int rabbitWaitTtl;

    @Value("${spring.rabbitmq.max-retries}")
    public int rabbitMaxRetries;

    @Value("${spring.rabbitmq.exchange-name}")
    public String rabbitExchangeName;

    @Value("${payment-orders.test.queue}")
    public String testQueue;

    @Value("${payment-orders.work.queue}")
    public String workQueue;

    @Value("${payment-orders.wait.queue}")
    public String waitQueue;

    @Value("${payment-orders.retry.queue}")
    public String retryQueue;

    @Value("${payment-orders.park.queue}")
    public String parkQueue;

    @Value("${payment-orders.test.routing-key}")
    public String testRoutingKey;

    @Value("${payment-orders.work.routing-key}")
    public String workRoutingKey;

    @Value("${payment-orders.wait.routing-key}")
    public String waitRoutingKey;

    @Value("${payment-orders.retry.routing-key}")
    public String retryRoutingKey;

    @Value("${payment-orders.park.routing-key}")
    public String parkRoutingKey;

    @Value("${max.retry}")
    public Integer maxRetry;
}