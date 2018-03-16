package middleware.request.handler;

import com.google.gson.Gson;
import middleware.request.handler.pojo.Middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * Created by Lingga on 01/03/18.
 */

public class Test
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 25; i++)
        {
            Middleware middleware = new Middleware();

            UUID uuid = UUID.randomUUID();

            middleware.setUuid(uuid.toString());

            //if even = admin, if odd = user
            if (i == 13)
            {
                middleware.setRole("stranger");
            }
            else
            {
                if (i % 2 == 0)
                {
                    middleware.setRole("admin");
                }
                else if (i % 2 != 0)
                {
                    middleware.setRole("user");
                }
                else
                {
                    middleware.setRole("stranger");
                }
            }

            System.out.println("Request: " + middleware);

            try
            {
                URL url = new URL("http://10.32.6.20:6613/middleware/test");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                OutputStream os = conn.getOutputStream();
                os.write(new Gson().toJson(middleware).getBytes());
                os.flush();

                if (conn.getResponseCode() != 200)
                {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output;

                while ((output = br.readLine()) != null)
                {
                    System.out.println("Response: " + output);
                }

                conn.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}