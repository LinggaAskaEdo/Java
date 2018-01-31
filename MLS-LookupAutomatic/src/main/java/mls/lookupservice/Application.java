package mls.lookupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Lingga on 23/03/16.
 */

@EnableScheduling
@PropertySources({
        @PropertySource("classpath:database.properties"),
        @PropertySource("classpath:messages.properties")
})
@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class);
    }
}