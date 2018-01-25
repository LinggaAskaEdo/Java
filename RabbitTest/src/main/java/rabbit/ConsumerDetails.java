package rabbit;

/**
 * Created by adi on 1/19/18.
 */
public class ConsumerDetails {

    private String consumer_tag;

//    private Map<String, Object> arguments;

    private CustomArguments arguments;

    private ChannelDetails channel_details;

    public String getConsumer_tag() {
        return consumer_tag;
    }

    public void setConsumer_tag(String consumer_tag) {
        this.consumer_tag = consumer_tag;
    }

    public CustomArguments getArguments() {
        return arguments;
    }

    public void setArguments(CustomArguments arguments) {
        this.arguments = arguments;
    }

    public ChannelDetails getChannel_details() {
        return channel_details;
    }

    public void setChannel_details(ChannelDetails channel_details) {
        this.channel_details = channel_details;
    }
}
