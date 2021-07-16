package testonline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestShipperAnagramIndex
{
    public static void main(String[] args)
    {
        String data1 = "abcbcadebca";
        String data2 = "abc";

        int length = data2.length();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < data1.length(); i++)
        {
            if (i + length <= data1.length())
            {
                String sample = data1.substring(i, i + length);
                System.out.print("index: " + i + ", data: " + sample);

                char[] arrData1 = sample.toCharArray();
                char[] arrData2 = data2.toCharArray();

                Arrays.sort(arrData1);
                Arrays.sort(arrData2);

                if (Arrays.equals(arrData1, arrData2))
                {
                    result.add(i);
                    System.out.print(" ---> anagram");
                }

                System.out.println();
            }
        }

        System.out.println(result);
    }
}