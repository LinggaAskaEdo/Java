package com.springboot.vertx;

import com.springboot.vertx.verticle.MainVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application
{
    private MainVerticle mainVerticle;

    @Autowired
    public Application(MainVerticle mainVerticle)
    {
        this.mainVerticle = mainVerticle;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void deployVerticle()
    {
        Vertx.vertx().deployVerticle(mainVerticle);
    }
}