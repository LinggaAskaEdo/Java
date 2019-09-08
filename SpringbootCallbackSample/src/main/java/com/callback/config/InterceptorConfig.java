package com.callback.config;

import id.co.homecredit.portal.common.core.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("id.co.homecredit.portal.common.core")
@EnableWebMvc
@EntityScan("id.co.homecredit")
public class InterceptorConfig extends WebMvcConfigurerAdapter
{
    private AuthenticationFilter authenticationFilter;

    @Autowired
    public InterceptorConfig(AuthenticationFilter authenticationFilter)
    {
        this.authenticationFilter = authenticationFilter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(authenticationFilter);
    }
}