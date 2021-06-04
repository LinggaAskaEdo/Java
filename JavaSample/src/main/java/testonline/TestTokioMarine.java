package testonline;

import java.util.ArrayList;
import java.util.List;

public class TestTokioMarine
{
    public static void main(String[] args)
    {
        String fruitMangga = "Mangga";

        //number 1
        String input = "SehaT";

        System.out.println(isPalindrome(input));

        //number 2
        List<String> array1 = new ArrayList<>();
        array1.add(fruitMangga);
        array1.add("Apel");
        array1.add("Melon");
        array1.add("Pisang");
        array1.add("Sirsak");
        array1.add("Tomat");
        array1.add("Nanas");
        array1.add("Nangka");
        array1.add("Timun");
        array1.add(fruitMangga);

        List<String> array2 = new ArrayList<>();
        array2.add("Bayam");
        array2.add("Wortel");
        array2.add("Kangkung");
        array2.add(fruitMangga);
        array2.add("Tomat");
        array2.add("Kembang Kol");
        array2.add("Nangka");
        array2.add("Timun");

        List<String> sameArr = findSame(array1, array2);
        System.out.println(sameArr);

        List<String> diffArr = findDiff(array1, array2);
        System.out.println(diffArr);

        //number 3
        printTriangle();
    }

    private static boolean isPalindrome(String input)
    {
        int i = 0;
        int j = input.length() - 1;

        while (i < j)
        {
            if (input.toLowerCase().charAt(i) != input.toLowerCase().charAt(j))
                return false;

            i++;
            j--;
        }

        return true;
    }

    private static List<String> findSame(List<String> array1, List<String> array2)
    {
        List<String> result = new ArrayList<>();

        for (String data : array1)
        {
            if (array2.contains(data) && !result.contains(data))
                result.add(data);
        }

        return result;
    }

    private static List<String> findDiff(List<String> array1, List<String> array2)
    {
        List<String> result = new ArrayList<>();

        for (String data : array1)
        {
            if (!array2.contains(data))
                result.add(data);
        }

        for (String data : array2)
        {
            if (!array1.contains(data))
                result.add(data);
        }

        return result;
    }

    private static void printTriangle()
    {
        int line = 1;
        int data = 5;
        int end = data + 12;
        boolean status = false;

        for (int x = data; x <= end; x++)
        {
            if (status)
                break;

            System.out.print(x);

            if (data != x)
            {
                for (int y = (x + 1); y <= data + (2 * (line - 1)); y++)
                {
                    System.out.print(" " + y);

                    if (y == end)
                        status = true;
                }
            }

            System.out.println();
            line++;
        }
    }
}