package soap.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by Lingga on 23/05/17.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld
{
    @WebMethod String getHelloWorldAsString(String name);
}