package numeric;

import java.util.Arrays;

public class RoundingFloat
{
    public static void main(String[] args)
    {
        double a = 3.25;
        double b = (a - Math.floor(a));

        System.out.println(0.2f > b);

        String strDouble = new Double(a).toString();
        String str = strDouble.substring(0, strDouble.indexOf('.'));

        if (b < 0.2)
        {
            int x = Integer.parseInt(str);
            System.out.println("x: " + x);
        }
        else
        {
            int x = Integer.parseInt(str) + 1;
            System.out.println("x: " + x);
        }
    }
}