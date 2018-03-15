package other;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by Lingga on 28/02/17.
 */

public class TestOther
{
    public static void main(String[] args)
    {
        String sample = "03.0000";
        DecimalFormat decimalFormat = new DecimalFormat("#");

        //System.out.println(Integer.parseInt(sample.trim()));

        try
        {
            int number = decimalFormat.parse(sample).intValue();
            if (number == 0)
                System.out.println("The number is: " + number);
        }
        catch (ParseException e)
        {
            System.out.println(sample + " is not a valid number.");
        }

        /*if (Integer.parseInt(sample) == 0)
        {
            System.out.println("AAA");
        }
        else
        {
            System.out.println("BBB");
        }*/
    }
}