package testonline;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSayurBox
{
    public static void main(String[] args)
    {
//        Scanner sc = new Scanner(System.in);
//
//        while (sc.hasNextInt())
//        {
//            int number = sc.nextInt();
//        }
//        Scanner keyboard = new Scanner(System.in);
//        int number = keyboard.nextInt();
//
//        List<Integer> arr = new ArrayList<>();
//
//        for (int i = 1; i <= number; i++)
//        {
//            if ((number != i) && (number % i == 0))
//                arr.add(i);
//        }
//
//        int total = arr.stream().mapToInt(Integer::intValue).sum();
//
//        if (total == number)
//            System.out.println(number + " perfect");
//        else if (Math.abs(number - total) <= 2)
//            System.out.println(number + " almost perfect");
//        else
//            System.out.println(number + " not perfect");
//
//        arr.clear();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt())
        {
            int number = scanner.nextInt();
            int total = 0;

            for (int i = 1; i <= Math.sqrt(number); i++)
            {
//                if ((number != i) && (number % i == 0))
                if (number % i == 0)
                {
//                    arr.add(i);
                    System.out.println("a. " + i);
                    total = total + i;

                    if ((number / i) != number)
                    {
                        System.out.println("b. " +number / i);
                        total = total + (number / i);
                    }

//                    if (number / i == i)
//                    {
//                        total = total + i;
//                        System.out.println("a." + i);
//                    }
//                    else
//                    {
//                        total = total + i;
//                        System.out.println("b." +i);
//
//                        total = total + (number / i);
//                        System.out.println("c." +number / i);
//                    }
                }
            }

            System.out.println("total= " + total);
//            int total = arr.stream().mapToInt(Integer::intValue).sum();
//            System.out.println(arr + " = " + total);
//            arr.clear();

            if (total == number)
                System.out.println(number + " perfect");
            else if (Math.abs(number - total) <= 2)
                System.out.println(number + " almost perfect");
            else
                System.out.println(number + " not perfect");
        }
    }
}