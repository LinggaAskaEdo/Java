package run.c.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Lingga on 10/10/17.
 */

public class RunC
{
    public static void main(String[] args)
    {
        String cFileName = "calculator.c";
        String cCompiledName = "calculator";

        try
        {
            Process compile = new ProcessBuilder("gcc", "-o" + cCompiledName, cFileName).start();
            compile.waitFor();

            Process execute = new ProcessBuilder("./calculator").start();

            if (execute.getInputStream().read() == -1)
            {
                System.out.println("ERRROR !");
            }
            else
            {
                //System.out.println("AAA: " + execute.getInputStream());
                //System.out.println("BBB: " + execute.getOutputStream().toString());

                BufferedReader reader = new BufferedReader(new InputStreamReader(execute.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null)
                {
                    System.out.println(line);
                    builder.append(line);
                    //builder.append(System.getProperty("line.separator"));
                }

                String result = builder.toString();

                System.out.println("Result: " + result);
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }

        /*try
        {
            Process compile = new ProcessBuilder("gcc", "-o" + cCompiledName, cFileName).start();

            Process execute = new ProcessBuilder("./" + cCompiledName);

             *//*if (compile.getInputStream().read() == -1)
            {
                // that means something was written to stderr, and you can do something like
                System.out.println("ERROR1 !");
                System.exit(-1);
            }

            if (compile.exitValue() == -1)
            {
                // that means something was written to stderr, and you can do something like
                System.out.println("ERROR2 !");
                System.exit(-1);
            }*//*

            System.out.println("Execute: " + execute);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
    }
}