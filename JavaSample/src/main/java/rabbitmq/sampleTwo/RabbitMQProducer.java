package rabbitmq.sampleTwo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

public class RabbitMQProducer
{
    public static void main(String[] args) throws IOException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("app");
        factory.setPassword("1rstwap");
        factory.setVirtualHost("/");
        factory.setHost("10.32.6.20");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "MyExchange";
        String routingKey = "MyRoutes";
        byte[] messageBodyBytes = "Hello, Lingga !!!".getBytes();

        channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes) ;
        channel.close();
        connection.close();
    }
}