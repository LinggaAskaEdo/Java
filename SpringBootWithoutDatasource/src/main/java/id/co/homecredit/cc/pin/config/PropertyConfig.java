package id.co.homecredit.cc.pin.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public final class PropertyConfig
{
    private PropertyConfig()
    {
        /*Prevents access default paramater-less constructor*/
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        final StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setConfig(EncryptedPropertiesConfig.createPBEConfig());

        return new EncryptedPropertiesConfig.PlaceholderConfigurer(standardPBEStringEncryptor);
    }
}