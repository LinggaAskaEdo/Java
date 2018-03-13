package soap.ws;

import javax.jws.WebService;

/**
 * Created by Lingga on 23/05/17.
 */

@WebService(endpointInterface = "soap.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld
{
    @Override
    public String getHelloWorldAsString(String name)
    {
        return "Hello World JAX-WS " + name;
    }
}