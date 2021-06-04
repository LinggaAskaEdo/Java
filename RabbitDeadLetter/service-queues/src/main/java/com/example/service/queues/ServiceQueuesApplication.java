package com.example.service.queues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiceQueuesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ServiceQueuesApplication.class, args);
    }
}