package string;

/**
 * Created by Lingga on 08/02/17.
 */

public class StringInteger
{
    public static void main(String[] args)
    {
        Integer a = null;

        if (a == null)
            a = 0;

        String b = Integer.toString(a);
        System.out.println(b);
    }
}