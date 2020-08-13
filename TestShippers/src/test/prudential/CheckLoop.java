package test.prudential;

import static java.lang.System.out;

public class CheckLoop
{
    public static void main(String[] args)
    {
        int[] arrA = { 1, 3, 6, 4, 1, 2 };
        int key = 0;
        boolean status = true;
        boolean found;

        while (status)
        {
            key++;
            out.println(key);

            found = checkExist(arrA, key);

            if (!found)
            {
                status = false;
            }
        }

        out.println("Result: " + key);
    }

    private static boolean checkExist(int[] arrA, int key)
    {
        boolean status = false;

        for (int j : arrA)
        {
            if (j == key)
            {
                status = true;
                break;
            }
        }

        return status;
    }
}
