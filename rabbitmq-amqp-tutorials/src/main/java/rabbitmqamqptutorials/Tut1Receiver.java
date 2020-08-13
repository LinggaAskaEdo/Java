package rabbitmqamqptutorials;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static java.lang.System.*;

@RabbitListener(queues = "hello")
public class Tut1Receiver
{
    @RabbitHandler
    public void receive(String in)
    {
        out.println(" [x] Received '" + in + "'");
    }
}