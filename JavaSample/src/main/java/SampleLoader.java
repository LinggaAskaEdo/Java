import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by edo on 14/12/16.
 */
public class SampleLoader
{
    public static void main(String[] args)
    {
        /*ClassLoader loader = Thread.currentThread().getContextClassLoader();

        *//*int i;

        try
        {
            FileReader fr = new FileReader(String.valueOf(loader.getResourceAsStream("XXX")));

            while((i = fr.read()) != -1)
                System.out.println((char)i);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*//*

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(loader.getResourceAsStream("XXX")))))
        {
            System.out.println(bufferedReader.readLine());
            //currentLine =  bufferedReader.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/

        //public static void main(String[] args) {
        Map<String, Object> param = new HashMap<>();
        System.out.println("test:"+ (SampleLoader)param.get(""));

    //}
    }
}