package rabbitmq.sampleOne;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * The producer endpoint that writes to the queue.
 * @author syntx
 *
 */
class Producer extends EndPoint
{
    Producer(String endPointName) throws IOException
    {
        super(endPointName);
    }

    void sendMessage(Serializable object) throws IOException
    {
        channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
    }
}