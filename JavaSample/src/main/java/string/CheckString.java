package string;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Lingga on 03/02/17.
 */

public class CheckString
{
    public static void main(String[] args)
    {
        String input = "sd2e32,asda,      \t";

        System.out.println(input);

        if (input.trim().length() > 0)
            System.out.println(true + " method 1");
        else
            System.out.println(false + " method 1");

        if (input.matches(".*\\w.*"))
            System.out.println(true + " method 2");
        else
            System.out.println(false + " method 2");

        if (StringUtils.isBlank(input))
            System.out.println(true + " method 3");
        else
            System.out.println(false + " method 3");

        int i = 0;
        while (i < 6)
        {
            if (i == 2)
            {
                System.out.println("xxx");
                i++;
                continue;
            }

            System.out.println(i);

            i++;
        }
    }
}