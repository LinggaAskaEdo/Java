package rabbit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by adi on 1/22/18.
 */
public class Util{

    public static final int SOCKET_DEFAULT_TIMEOUT_MS = 10000;

    public String socketCall (String host, int port, String msg, int timeout) throws IOException {
        String response = null;
        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = new Socket(host, port);
        socket.setSoTimeout(timeout);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println(msg);
        response = in.readLine();
        System.out.println("Socket OUT>>:"+msg+"||Socket IN<<:"+response);
        out.close();
        in.close();
        socket.close();

        return response;
    }

}
