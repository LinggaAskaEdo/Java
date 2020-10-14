package com.sql2o.hexagonal.adapter.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring4.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfiguration
{
    ApplicationConfiguration()
    {
        /*Do Nothing*/
    }

    @Bean
    public static EnvironmentStringPBEConfig environmentVariablesConfiguration()
    {
        EnvironmentStringPBEConfig environmentVariablesConfiguration = new EnvironmentStringPBEConfig();
        environmentVariablesConfiguration.setAlgorithm("PBEWithMD5AndDES");
        environmentVariablesConfiguration.setPassword("M3k1J4mur");

        return environmentVariablesConfiguration;
    }

    @Bean
    public static StandardPBEStringEncryptor configurationEncryptor()
    {
        StandardPBEStringEncryptor configurationEncryptor = new StandardPBEStringEncryptor();
        configurationEncryptor.setConfig(environmentVariablesConfiguration());

        return configurationEncryptor;
    }

    @Bean
    public static EncryptablePropertyPlaceholderConfigurer propertyConfigurer()
    {
        EncryptablePropertyPlaceholderConfigurer placeholderConfigurer = new EncryptablePropertyPlaceholderConfigurer(configurationEncryptor());
        placeholderConfigurer.setIgnoreResourceNotFound(true);

        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(new ClassPathResource("cache.properties"));
        resourceList.add(new ClassPathResource("database.properties"));
        resourceList.add(new ClassPathResource("executor.properties"));
        resourceList.add(new ClassPathResource("web-service.properties"));
        resourceList.add(new ClassPathResource("queue.properties"));
        resourceList.add(new FileSystemResource("/etc/myapp/overriding.properties"));

        placeholderConfigurer.setLocations(resourceList.toArray(new Resource[]{}));

        return placeholderConfigurer;
    }
}