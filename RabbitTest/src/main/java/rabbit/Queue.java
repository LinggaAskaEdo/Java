package rabbit;

import java.util.List;

/**
 * Created by adi on 1/19/18.
 */
public class Queue {

    List<ConsumerDetails> consumer_details;

    public List<ConsumerDetails> getConsumer_details() {
        return consumer_details;
    }

    public void setConsumer_details(List<ConsumerDetails> consumer_details) {
        this.consumer_details = consumer_details;
    }
}
