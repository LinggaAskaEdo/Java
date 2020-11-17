package com.sql2o.hexagonal.adapter.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({ "classpath:cache.properties", "classpath:database.properties", "classpath:executor.properties", "classpath:web-service.properties" })
public class ConfigPreference
{
    @Value("${executor.core.pool.size}")
    public int executorCorePoolSize;

    @Value("${executor.max.pool.size}")
    public int executorMaxPoolSize;

    @Value("${executor.queue.capacity}")
    public int executorQueueCapacity;

    @Value("${db.url}")
    public String dbUrl;

    @Value("${db.username}")
    public String dbUser;

    @Value("${db.password}")
    public String dbPassword;

    @Value("${db.driverClassName}")
    public String dbDriverClassName;

    @Value("${db.minIdle}")
    public int dbMinIdle;

    @Value("${db.maxIdle}")
    public int dbMaxIdle;

    @Value("${db.maxOpenPreparedStatements}")
    public int dbMaxOpenPreparedStatements;

    @Value("${rest.timeout.connection}")
    public int restTimeout;

    @Value("${omdb.api.url}")
    public String omdbApiUrl;

    @Value("${omdb.api.key}")
    public String omdbApiKey;

    @Value("${cache.token}")
    public String cacheToken;

    @Value("${cache.expiry.bank}")
    public long cacheExpiryBank;

    @Value("${cache.expiry.movie}")
    public long cacheExpiryMovie;

    @Value("${cache.expiry.student}")
    public long cacheExpiryStudent;

    @Value("${spring.rabbitmq.host}")
    public String rabbitmqHost;

    @Value("${spring.rabbitmq.port}")
    public int rabbitmqPort;

    @Value("${spring.rabbitmq.username}")
    public String rabbitmqUsername;

    @Value("${spring.rabbitmq.password}")
    public String rabbitmqPassword;

    @Value("${redis.host}")
    public String redisHost;

    @Value("${redis.port}")
    public int redisPort;

    @Value("${redis.database}")
    public int redisDatabase;
}