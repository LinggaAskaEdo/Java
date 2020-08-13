package test.prudential;

import java.util.Arrays;

public class ConcurrentString
{
    public static void main(String[] args)
    {
//        String example = "ACCAABBC";
//        String example = "ABCBBCBA";
        String example = "BABABA";

        int i = 0;
        boolean status = true;
        System.out.println(example);

        while (status)
        {
            System.out.println("Length: " + example.length());

            if (!(example.length() == (i + 1)) && example.length() != 0)
            {
                System.out.println(i + " = " + example.charAt(i) + ", " + (i + 1) + " = "+ example.charAt(i + 1));

                if (example.charAt(i) == example.charAt(i + 1))
                {
                    example = charRemoveAt(example, i);
                    example = charRemoveAt(example, i);

                    System.out.println(example);

                    i = 0;
                }
                else
                {
                    i++;
                }

                System.out.println("Counter: " + i);
            }
            else
            {
                status = false;
//                break;
            }
        }

        System.out.println("Result: " + example);
    }

    public static String charRemoveAt(String str, int p)
    {
        return str.substring(0, p) + str.substring(p + 1);
    }
}
