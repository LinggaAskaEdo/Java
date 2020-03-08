package com.springboot.vertx.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigPreference
{
    @Value("${http.port}")
    public int httpPort;

    @Value("${app.id}")
    public String appId;

    @Value("${app.key}")
    public String appKey;

    @Value("${rest.timeout}")
    public long restTimeout;

    @Value("${database.url}")
    public String databaseUrl;

    @Value("${database.driver}")
    public String databaseDriver;

    @Value("${database.user}")
    public String databaseUser;

    @Value("${database.password}")
    public String databasePassword;

    @Value("${database.host}")
    public String databaseHost;

    @Value(("${database.port}"))
    public int databasePort;

    @Value(("${database.name}"))
    public String databaseName;

    @Value("${database.pool}")
    public int databasePool;
}