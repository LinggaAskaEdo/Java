package com.hexagonal.sample.adapters.config;

import com.hexagonal.sample.HexagonalArchSampleApplication;
import com.hexagonal.sample.adapters.persistence.BankAccountRepository;
import com.hexagonal.sample.application.service.BankAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = HexagonalArchSampleApplication.class)
public class BeanConfiguration
{
    @Bean
    BankAccountService bankAccountService(BankAccountRepository repository)
    {
        return new BankAccountService(repository, repository);
    }
}