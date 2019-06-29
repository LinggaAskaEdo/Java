package com.sba.test;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminTestApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(SpringBootAdminTestApplication.class);
    }

    public static void main(String[] args)
    {
        System.setProperty("jasypt.encryptor.password", "password");
        SpringApplication.run(SpringBootAdminTestApplication.class);
    }
}