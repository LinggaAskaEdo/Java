package soap.endpoint;

import soap.ws.HelloWorldImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by Lingga on 29/05/17.
 */

public class HelloWorldPublisher
{
    public static void main(String[] args)
    {
        Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
    }
}