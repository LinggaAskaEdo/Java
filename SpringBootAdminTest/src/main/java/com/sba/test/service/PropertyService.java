package com.sba.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyService
{
    @Value("${security.user.name}")
    public String securityUserName;

    @Value("${security.user.password}")
    public String securityUserPassword;

//    public String getSecurityUserName()
//    {
//        return securityUserName;
//    }

//    public String getSecurityUserPassword()
//    {
//        return securityUserPassword;
//    }

//    public String getPasswordUsingEnvironment(Environment environment)
//    {
//        return environment.getProperty("security.user.password");
//    }
}