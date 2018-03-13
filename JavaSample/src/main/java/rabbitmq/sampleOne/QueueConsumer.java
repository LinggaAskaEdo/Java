package rabbitmq.sampleOne;

import com.rabbitmq.client.*;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.Map;

/**
 * The endpoint that consumes messages off of the queue. Happens to be runnable.
 * @author syntx
 *
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer
{
    QueueConsumer(String endPointName) throws IOException
    {
        super(endPointName);
    }

    public void run()
    {
        try
        {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true,this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag)
    {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    /**
     * Called when new message is available.
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
    {
        Map map = SerializationUtils.deserialize(body);
        System.out.println("Message Number " + map.get("messageNumber") + " received.");
    }

    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}