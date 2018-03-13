package rabbitmq.sampleOne;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Main
{
    private Main() throws Exception
    {
        QueueConsumer consumer = new QueueConsumer("TestQueue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("TestQueue");

        for (int i = 0; i < 10; i++)
        {
            HashMap<String, Integer> message = new HashMap<>();
            message.put("messageNumber", i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }
    }

    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception
    {
        new Main();
    }
}