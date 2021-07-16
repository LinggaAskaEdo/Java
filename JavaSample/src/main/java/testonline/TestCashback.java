package testonline;

import java.util.ArrayList;
import java.util.List;

public class TestCashback
{
    public static void main(String[] args)
    {
        int temp = 0;
        int[] arr = { 80, 5, 99, 17, 73, 38, 24, 84, 66, 82, 22, 4, 52, 71, 85, 1, 89, 56, 18, 41 };
        List<Integer> arrTemp = new ArrayList<>();

        for (int x : arr)
        {
            for (int y : arr)
            {
                if ((x < y) && (x > temp))
                {
                        temp = y;
                }
            }

            arrTemp.add(temp);
        }

        System.out.println(arrTemp);
    }
}
