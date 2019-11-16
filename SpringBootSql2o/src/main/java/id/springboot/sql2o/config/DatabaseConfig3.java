package id.springboot.sql2o.config;

import id.springboot.sql2o.preference.ConfigPreference;
import id.springboot.sql2o.util.ColumnsMapper;
import id.springboot.sql2o.util.OracleDateConverter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;
import org.sql2o.converters.Converter;
import org.sql2o.quirks.NoQuirks;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfig3
{
    private ConfigPreference preference;
    private ColumnsMapper mapper;
    private OracleDateConverter oracleDateConverter;

    @Autowired
    public DatabaseConfig3(ConfigPreference preference, ColumnsMapper mapper, OracleDateConverter oracleDateConverter)
    {
        this.preference = preference;
        this.mapper = mapper;
        this.oracleDateConverter = oracleDateConverter;
    }

    @Bean(name = "sql2oDS")
    public DataSource dataSource()
    {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(preference.datasourceUrl);
        dataSource.setUsername(preference.datasourceUsername);
        dataSource.setPassword(preference.datasourcePassword);
        dataSource.setDriverClassName(preference.datasourceDriver);

        return dataSource;
    }

    @Autowired
    @Bean
    public Sql2o sql2o(@Qualifier("sql2oDS") DataSource dataSource)
    {
        final Map<Class, Converter> mappers = new HashMap<>();
        mappers.put(Date.class, oracleDateConverter);

//        Sql2o sql2o = new Sql2o(dataSource);
        Sql2o sql2o = new Sql2o(dataSource, new NoQuirks(mappers));
//        sql2o.setDefaultColumnMappings(mapper.generateMapper());

        return sql2o;

//        return new Sql2o(dataSource);
    }
}