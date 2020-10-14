package com.sql2o.hexagonal.adapter.config;

import com.sql2o.hexagonal.Application;
import com.sql2o.hexagonal.adapter.dao.BankDao;
import com.sql2o.hexagonal.adapter.dao.StudentDao;
import com.sql2o.hexagonal.adapter.ws.MovieREST;
import com.sql2o.hexagonal.application.service.BankService;
import com.sql2o.hexagonal.application.service.MovieService;
import com.sql2o.hexagonal.application.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class BeanConfiguration
{
    @Bean
    StudentService studentService(StudentDao studentDao)
    {
        return new StudentService(studentDao);
    }

    @Bean
    BankService bankService(BankDao bankDao)
    {
        return new BankService(bankDao);
    }

    @Bean
    MovieService movieService(MovieREST movieREST)
    {
        return new MovieService(movieREST);
    }
}