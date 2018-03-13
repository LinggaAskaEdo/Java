package bbw.pulsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Lingga on 12/03/18.
 */

@ComponentScan(basePackages = "bbw.pulsa")
@PropertySource(value = { "classpath:application.properties" })
@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class);
    }
}