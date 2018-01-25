package rabbit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.concurrent.TimeoutException;

/**
 * Created by adi on 12/15/17.
 */
public class TestGetConsumerCount {

    public static String username = "app";
    public static String password = "1rstwap";
    public static String virtualHost = "/";
//    public static String host = "10.32.6.22";
    public static String host = "masstrax.ssd.ams"; //masstrax.ssd.ams 10.32.6.188
    public static int port = 5672;
    public static int managementPort = 15672;


    public static boolean isShovelExist (String urlString, String shovelName){
        if(shovelName == null){
            return false;
        }

        boolean isExist = false;
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlString + "/" + shovelName);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            String userpass = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            con.setRequestProperty ("Authorization", basicAuth);

            inputStream = con.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                sb.append(line + "\n");
            }
            String resultString = sb.toString();

            if(resultString!=null && !resultString.isEmpty()){
                JsonNode jsonNode = new ObjectMapper().readTree(resultString);
                isExist = ("\""+shovelName+"\"").equals(jsonNode.get("name").toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isExist;
    }

    public static boolean createShovel (String urlString, String srcQueue, String destQueue, String shovelName){
        boolean isSuccess = false;

        if(srcQueue == null || destQueue == null || shovelName == null){
            return false;
        }

        InputStream inputStream = null;
        BufferedReader br = null;
        DataOutputStream dOut = null;
        try {
            URL url = new URL(urlString + "/" + shovelName);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");

            String userpass = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            con.setRequestProperty ("Authorization", basicAuth);

            String json = "{\"value\":{\"src-uri\": \"amqp://\", \"src-queue\": \""+srcQueue+"\", \"dest-uri\": \"amqp://\", \"dest-queue\": \""+destQueue+"\"}}";
            byte[] postData = json.getBytes("UTF-8");
            dOut = new DataOutputStream(con.getOutputStream());
            dOut.write(postData);
            dOut.flush();

            inputStream = con.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                sb.append(line + "\n");
            }
            String resultString = sb.toString();
            System.out.println(con.getResponseCode());

            return con.getResponseCode() == 204;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (inputStream != null)
                    inputStream.close();
                if (dOut != null)
                    dOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }

    public static boolean deleteShovel (String urlString, String shovelName){
        boolean isSuccess = false;

        if(shovelName == null){
            return false;
        }

        InputStream inputStream = null;
        BufferedReader br = null;
        DataOutputStream dOut = null;
        try {
            URL url = new URL(urlString + "/" + shovelName);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("DELETE");

            String userpass = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            con.setRequestProperty ("Authorization", basicAuth);


            inputStream = con.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                sb.append(line + "\n");
            }
            String resultString = sb.toString();
            System.out.println(con.getResponseCode());

            return con.getResponseCode() == 204;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (inputStream != null)
                    inputStream.close();
                if (dOut != null)
                    dOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String shovelQueueSrc = "routerAdi";
        String shovelQueueDest = "sharedRouter";
        String shovelName = shovelQueueSrc + "Shovel";
        boolean isShovelExist = false;

//        URL url = new URL("http://"+username+":"+password+"@"+host+":"+managementPort+"/api/parameters/shovel/%2f/"+shovelName);
        String urlString = "http://"+host+":"+managementPort+"/api/parameters/shovel/"+ URLEncoder.encode(virtualHost, "UTF-8");

        //check shovel is created via api;
        isShovelExist = isShovelExist(urlString, shovelName);

        System.out.println("is shovel already exist:"+isShovelExist);

        while(true) {
            try{
                AMQP.Queue.DeclareOk routerAdi = channel.queueDeclarePassive(shovelQueueSrc);
                System.out.println("routerAdi.getConsumerCount():"+routerAdi.getConsumerCount());
                if(routerAdi.getConsumerCount()<=0 + (isShovelExist?1:0)){
                    if(!isShovelExist){
                        //create shovel
                        System.out.println("Creating shovel:" + shovelName);
                        isShovelExist = createShovel(urlString, shovelQueueSrc, shovelQueueDest, shovelName);
                    }
                }else{
                    if(isShovelExist){
                        //drop shovel
                        System.out.println("Deleting shovel" + shovelName);
                        isShovelExist = !deleteShovel(urlString, shovelName);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            Thread.sleep(500);
        }
    }
}
