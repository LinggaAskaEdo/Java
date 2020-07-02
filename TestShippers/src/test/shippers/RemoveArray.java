package test.shippers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RemoveArray
{
    public static void main(String[] args)
    {
        System.out.print("Please enter number of input string (1::9): ");
        Scanner inputTotal = new Scanner(System.in);
        int total = Integer.parseInt(inputTotal.nextLine());

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < total; i++)
        {
            System.out.print("String " + (i + 1) + ": ");
            Scanner inputName = new Scanner(System.in);
            String name = inputName.nextLine();

            //skip adding if exist
            if (!checkExisting(stringList, name))
                stringList.add(name);
        }

        //sort list asc
        Collections.sort(stringList);

        //print out with lower case
        for (String x : stringList)
        {
            System.out.println(x.toLowerCase());
        }
    }

    private static boolean checkExisting(List<String> stringList, String inputName)
    {
        boolean status = false;

        for (String data: stringList)
        {
            if (data.equalsIgnoreCase(inputName))
            {
                status = true;
                break;
            }
        }

        return status;
    }
}