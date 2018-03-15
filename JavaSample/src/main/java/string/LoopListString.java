package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lingga on 21/07/17.
 */

public class LoopListString
{
    public static void main(String[] args)
    {
        List<String> list;

        list = generateList();

        for (String temp : list)
        {
            System.out.println(temp);
        }
    }

    private static List<String> generateList()
    {
        List<String> result = new ArrayList<>();

        result.add("AAA");
        result.add("BBB");
        result.add("CCC");

        return result;
    }
}