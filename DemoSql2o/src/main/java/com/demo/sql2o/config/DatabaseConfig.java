package com.demo.sql2o.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.NonNull;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DatabaseConfig implements TransactionManagementConfigurer
{
    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.driverClassName}")
    private String dbDriverClassName;

    @Value("${db.minIdle}")
    private int dbMinIdle;

    @Value("${db.maxIdle}")
    private int dbMaxIdle;

    @Value("${db.maxOpenPreparedStatements}")
    private int dbMaxOpenPreparedStatements;

    @Bean
    DataSource dataSource()
    {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setMinIdle(dbMinIdle);
        dataSource.setMaxIdle(dbMaxIdle);
        dataSource.setMaxOpenPreparedStatements(dbMaxOpenPreparedStatements);

        return dataSource;
    }

    @Bean
    @NonNull
    @Override
    public TransactionManager annotationDrivenTransactionManager()
    {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public Sql2o sql2o()
    {
        return new Sql2o(dataSource());
    }
}