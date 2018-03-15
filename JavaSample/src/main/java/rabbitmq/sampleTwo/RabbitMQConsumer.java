package rabbitmq.sampleTwo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class RabbitMQConsumer
{
    public static void main(String []args) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("app");
        factory.setPassword("1rstwap");
        factory.setVirtualHost("/");
        factory.setHost("10.32.6.20");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = "MyQueue";
        String exchangeName = "MyExchange";
        String routingKey = "MyRoute";

        channel.exchangeDeclare(exchangeName, "direct", true);
        channel.queueDeclare(queueName, true,false,false,null);
        channel.queueBind(queueName, exchangeName, routingKey);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, false, consumer);

        while (true)
        {
            QueueingConsumer.Delivery delivery;

            try
            {
                delivery = consumer.nextDelivery();
            }
            catch (InterruptedException ie)
            {
                continue;
            }

            System.out.println("Message received " + new String(delivery.getBody()));
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}