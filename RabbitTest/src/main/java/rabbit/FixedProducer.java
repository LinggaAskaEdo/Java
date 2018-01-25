package rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by adi on 1/25/18.
 */
public class FixedProducer {

    private final ConnectionFactory factory;
    private final Connection conn;
    private final String host;
    private final int port;
    private final String userName;
    private final String password;
    private final String virtualHost;

    public FixedProducer(String host, int port, String userName, String password, String virtualHost) throws IOException, TimeoutException {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.virtualHost = virtualHost;
        factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(host);
        factory.setPort(port);
        factory.setAutomaticRecoveryEnabled(true);
        conn = factory.newConnection();
    }

    public void publishZeroToNine(String topic, String routingKey, long iteration, long interval) throws IOException {

        Channel channel = conn.createChannel();

        for (int i = 0 ; i < iteration ; i++){
            for (int j = 0 ; j < 10 ; j++){
                String msg = String.valueOf(j);
                System.out.println("Sending["+msg+"]");
                channel.basicPublish(topic, routingKey, null, msg.getBytes());
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void main(String[] args) throws IOException, TimeoutException {

        String userName = "app";
        String password = "1rstwap";
        String host = "10.32.6.20";
        int port = 5672;
        String virtualHost = "/";
        String topic = "amq.topic";

        FixedProducer fixedProducer = new FixedProducer(host, port, userName, password, virtualHost);

        fixedProducer.publishZeroToNine(topic, "queueA", 2, 300);


    }
}
