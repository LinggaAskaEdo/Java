package download;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Lingga on 04/05/17.
 */

public class HttpURLConnectionExample
{

    public static void main(String[] args)
    {
        HttpURLConnectionExample http = new HttpURLConnectionExample();

        System.out.println("Testing 1 - Get Detail of File");
        http.sendGetDetail();
    }

    private void sendGetDetail()
    {
        String url = "https://d3r3tk5171bc5t.cloudfront.net/export/MLS-full-cell-export-2017-05-04T000000.csv.gz";

        HttpURLConnection con = null;
        InputStream inputStream;
        OutputStream outputStream;

        try
        {
            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            int contentLength = con.getContentLength();
            //long lastModified = con.getLastModified();
            String lastModified = con.getHeaderField("Last-Modified");

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("\nResponse Code : " + responseCode);
            System.out.println("\nContent Length : " + contentLength);
            System.out.println("\nLast Modified : " + lastModified);

            //add request range
            //con.setRequestProperty("If-Range", lastModified);
            //con.setRequestProperty("Range", String.valueOf(contentLength - (contentLength - 10)));

            inputStream = new BufferedInputStream(con.getInputStream());
            outputStream = new FileOutputStream(new File("/home/edo/Downloads/MLS-full-cell-export-2017-05-04T000000-part.csv.gz"));

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1)
            {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("Done!");

            /*BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());*/
        }
        catch (MalformedURLException e)
        {
            System.out.println("\nMalformedURLException: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("\nIOException: " + e.getMessage());
        }
        finally 
        {
            con.disconnect();
        }
    }

    private boolean checkFileExist(String fullPath)
    {
        return false;
    }
}