package id.co.order.track;

import id.co.order.track.service.OrderTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Main implements CommandLineRunner
{
    private final OrderTrackService service;

    @Autowired
    public Main(OrderTrackService service)
    {
        this.service = service;
    }

    public static void main(String[] args)
    {
        new SpringApplicationBuilder(Main.class).headless(false).run(args);
    }

    @Override
    public void run(String... args)
    {
        service.processData(args[0], args[1]);
    }
}