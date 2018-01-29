package rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * Created by adi on 1/23/18.
 */
public class MyConsumer {

    private final String consumerTag;
    private DefaultConsumer defaultConsumer;
    private Channel channel;
    private boolean isCancelled = false;

    public MyConsumer(String consumerTag, DefaultConsumer defaultConsumer, Channel channel){
        this.consumerTag = consumerTag;
        this.defaultConsumer = defaultConsumer;
        this.channel = channel;
    }

    public MyConsumer(String consumerTag){
        this.consumerTag = consumerTag;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public DefaultConsumer getDefaultConsumer() {
        return defaultConsumer;
    }

    public Channel getChannel() {
        return channel;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancelConsumer() {
        isCancelled = true;
        if(channel != null && consumerTag != null){
            try {
                channel.basicCancel(consumerTag);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static MyConsumer findFirstUncancelled (List<MyConsumer> myConsumers){
        if(myConsumers != null){
            for (MyConsumer myConsumer: myConsumers) {
                if(myConsumer != null && !myConsumer.isCancelled){
                    return myConsumer;
                }
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MyConsumer && obj != null){
            if(obj == this){
                return true;
            }
            MyConsumer other = (MyConsumer)obj;
            return getConsumerTag() != null && other.getConsumerTag() != null && getConsumerTag().equals(other.getConsumerTag());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumerTag);
    }

    @Override
    public String toString() {
        return consumerTag;
    }
}
