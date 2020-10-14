package com.sql2o.hexagonal.adapter.config;

import com.sql2o.hexagonal.adapter.preference.ConfigPreference;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.NonNull;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration implements TransactionManagementConfigurer
{
    private final ConfigPreference preference;

    public DatabaseConfiguration(ConfigPreference preference)
    {
        this.preference = preference;
    }

    @Bean
    DataSource dataSource()
    {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(preference.dbUrl);
        dataSource.setUsername(preference.dbUser);
        dataSource.setPassword(preference.dbPassword);
        dataSource.setDriverClassName(preference.dbDriverClassName);
        dataSource.setMinIdle(preference.dbMinIdle);
        dataSource.setMaxIdle(preference.dbMaxIdle);
        dataSource.setMaxOpenPreparedStatements(preference.dbMaxOpenPreparedStatements);

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
