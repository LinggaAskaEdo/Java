package springboot.lpe.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import springboot.lpe.preference.Preference;

@Configuration
@PropertySource("file:/D://Apps/application.properties")
//@PropertySource("classpath:application.properties")
public class TestConfig
{
    private Preference preference;
    //    private final EncryptionUtil util;

    @Autowired
    public TestConfig(Preference preference)
    {
        this.preference = preference;
    }

//    @Autowired
//    public TestConfig(Preference preference, EncryptionUtil util)
//    {
//        this.preference = preference;
//        this.util = util;
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        final StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setConfig(EncryptedProperties.createPBEConfig());

        return new EncryptedProperties.PlaceholderConfigurer(standardPBEStringEncryptor);
    }

    @Bean
    public boolean test()
    {
        System.out.println("YUHUUU 1: " + preference.getNoteOne());
        System.out.println("YUHUUU 2: " + preference.getNoteTwo());
        System.out.println("YUHUUU 3: " + preference.getNoteThree());
        System.out.println("YUHUUU 4: " + preference.getNoteFour());
        System.out.println("YUHUUU 5: " + preference.getNoteFive());
//        System.out.println("YUHUUU 3: " + util.decrypt(preference.getNoteThree()));

        return true;
    }
}