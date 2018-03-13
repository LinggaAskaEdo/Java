package rabbitmq.sampleOne;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public abstract class EndPoint
{
    Channel channel;
    private Connection connection;
    String endPointName;

    EndPoint(String endPointName) throws IOException
    {
        this.endPointName = endPointName;

        //Create connection factory
        ConnectionFactory factory = new ConnectionFactory();

        //hostname of your rabbitmq server
        factory.setHost("10.32.6.20");
        factory.setUsername("app");
        factory.setPassword("1rstwap");

        //getting a connection
        connection = factory.newConnection();

        //creating a channel
        channel = connection.createChannel();

        //declaring a queue for this channel. If queue doesn't exist, it will be created on the server
        channel.queueDeclare(endPointName, false, false, false, null);
    }

    /**
     * Close channel and connection. Not necessary as it happens implicitly any way.
     * @throws IOException
     */
    public void close() throws IOException
    {
        this.channel.close();
        this.connection.close();
    }
}