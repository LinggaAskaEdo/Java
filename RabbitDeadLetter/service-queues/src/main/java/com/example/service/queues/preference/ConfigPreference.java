package com.example.service.queues.preference;

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
}