package org.o7planning.sbjdbctrans.config;

import id.co.homecredit.mobile.common.secure.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("id.co.homecredit.mobile.common.secure")
public class InterceptorConfig implements WebMvcConfigurer
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