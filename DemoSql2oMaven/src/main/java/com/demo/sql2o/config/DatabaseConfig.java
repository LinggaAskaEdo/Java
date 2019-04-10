package com.demo.sql2o.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig
{
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    @Bean(name = "sql2oPrimary")
    public Sql2o sql2oPrimary (DataSource datasource)
    {
        return new Sql2o(datasource);
    }
}