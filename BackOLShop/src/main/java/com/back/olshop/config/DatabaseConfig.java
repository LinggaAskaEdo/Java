package com.back.olshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig
{
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver-class-name"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    /*@Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource)
    {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        return jdbcTemplate;
    }

    /*@Bean
    public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager)
    {
        return new TransactionTemplate(transactionManager);
    }*/
}