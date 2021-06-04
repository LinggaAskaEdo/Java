package testonline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestTabsquare
{
    public static void main(String[] args)
    {
        /*Find duplicate characters and give result as sorted String
        example : BABCADED
        result : ABD */

        List<String> alpha = new ArrayList<>();
        alpha.add("B");
        alpha.add("A");
        alpha.add("B");
        alpha.add("C");
        alpha.add("A");
        alpha.add("D");
        alpha.add("E");
        alpha.add("D");

        List<String> result = new ArrayList<>();
        List<String> finalResult = new ArrayList<>();

        for (String data : alpha)
        {
            if (!result.contains(data))
            {
                result.add(data);
            }
            else
            {
                finalResult.add(data);
            }
        }

        Collections.sort(finalResult);
        System.out.println(finalResult);
    }
}