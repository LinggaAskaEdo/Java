package mls.lookupservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

/**
 * Created by Lingga on 23/03/17.
 */

@Configuration
public class MultipleDBConfig
{
    @Bean(name = "dsPrime")
    @Primary
    @ConfigurationProperties(prefix = "datasource.primary")
    public DataSource dataSourcePrimary()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dsSecond")
    @ConfigurationProperties(prefix = "datasource.secondary")
    public DataSource dataSourceSecondary()
    {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    @Bean(name = "sql2oPrimary")
    public Sql2o sql2oPrimary (@Qualifier("dsPrime") DataSource datasource)
    {
        return new Sql2o(datasource);
    }

    @Autowired
    @Bean(name = "sql2oSecondary")
    public Sql2o sql2oSecondary (@Qualifier("dsSecond") DataSource datasource)
    {
        return new Sql2o(datasource);
    }
}