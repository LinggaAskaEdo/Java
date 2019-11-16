package id.springboot.sql2o.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigPreference
{
    @Value("${spring.datasource.url}")
    public String datasourceUrl;

    @Value("${spring.datasource.username}")
    public String datasourceUsername;

    @Value("${spring.datasource.password}")
    public String datasourcePassword;

    @Value("${spring.datasource.driver-class-name}")
    public String datasourceDriver;
}