package test.traveloka;

import java.util.Arrays;

import static java.lang.System.out;

public class Test3
{
    public static void main(String[] args)
    {
//        int result = 0;
//        int total = 5;
//        int k = 3;

//        int[] numbers = new int[k];
//
//        for (int i = 0; i < k; i++)
//        {
//            numbers[i] = i + 1;
//        }
//
//        System.out.println(Arrays.toString(numbers));
//
//        int[] integers = new int[total];
//
//        for (int x = 0; x < total; x++)
//        {
//            integers[x] = numbers[0];
//        }
//
//        System.out.println(Arrays.toString(integers));
//
//        if (k == 1)
//        {
//            result = 1;
//        }
//        else
//        {
//
//        }
//
//        for (int x = 0; x < total; x++)
//        {
//            if (IntStream.of(integers).sum() == total)
//            {
//                result += 1;
//            }
//
//            if (integers[total - (x + 1)] + integers[total - (x + 2)] <= k)
//            {
//                result += 1;
//            }
//        }

        int total = 842;
        int k = 91;
        out.println("Count for " + total + " is " + count(total, k));
    }

    static int count(int total, int k)
    {
        // table[i] will store count of solutions for
        // value i.
        int[] table = new int[total + 1];
        int i;

        // Initialize all table values as 0
        Arrays.fill(table, 0);

        // Base case (If given value is 0)
        table[0] = 1;

        // One by one consider given 3
        // moves and update the table[]
        // values after the index greater
        // than or equal to the value of
        // the picked move

        for (int x = 1; x <= k; x++)
        {
            for (i = x; i <= total; i++)
            {
                out.println("i: " + i);
                out.println("x: " + x);

                if (i - x <= 0)
                    table[i] += table[i - x];
            }
        }

        return table[total];
    }
}
