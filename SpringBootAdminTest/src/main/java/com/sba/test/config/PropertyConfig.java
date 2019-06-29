package com.sba.test.config;

import com.sba.test.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class PropertyConfig
{
    /*Do nothing*/

    private PropertyService propertyService;

    @Autowired
    public PropertyConfig(PropertyService propertyService)
    {
        this.propertyService = propertyService;
    }

    @Bean
    public boolean test()
    {
        System.out.println("YUHUUU 1: " + propertyService.securityUserName);
        System.out.println("YUHUUU 2: " + propertyService.securityUserPassword);

        return true;
    }
}