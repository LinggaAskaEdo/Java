package com.demo.graphql.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertyConfig
{
    private static final String PRIVATE_KEY = "M3k1J4mur";
    private static final String ALGORITHM = "PBEWithMD5AndDES";

    PropertyConfig()
    {
        //Prevents access default paramater-less constructor
    }

    @Bean(name = "encryptorBean")
    public StringEncryptor stringEncryptor()
    {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(PRIVATE_KEY);
        config.setAlgorithm(ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);

        return encryptor;
    }
}