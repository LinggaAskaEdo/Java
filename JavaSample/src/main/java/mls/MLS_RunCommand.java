package mls;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Lingga on 25/04/17.
 */

public class MLS_RunCommand
{
    public static void main(String[] args)
    {
        boolean status = false;
        BufferedReader stdInput;
        BufferedReader stdError;
        //String s;

        try
        {
            //run the unix "ps -def" command
            //using the Runtime exec method
            Process p = Runtime.getRuntime().exec("ps -def");

            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            //read the output from the command
            if (stdInput.readLine() != null)
            {
                status = true;

                /*System.out.println("Here is the standard output of the command:\n");
                while ((s = stdInput.readLine()) != null)
                {
                    System.out.println(s);
                }*/
            }

            // read any errors from the attempted command
            if (stdError.readLine() != null)
            {
                status = false;

                /*System.out.println("Here is the standard error of the command (if any):\n");
                while ((s = stdError.readLine()) != null)
                {
                    System.out.println(s);
                }*/
            }

            System.out.println("Status: " + status);
            System.exit(0);
        }
        catch (Exception e)
        {
            System.out.println("Exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}