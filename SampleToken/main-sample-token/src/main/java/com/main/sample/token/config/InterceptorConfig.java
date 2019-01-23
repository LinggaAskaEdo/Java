package com.main.sample.token.config;

import com.common.sample.token.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.common.sample.token")
@EnableWebMvc
@EntityScan("com.main.sample.token")
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