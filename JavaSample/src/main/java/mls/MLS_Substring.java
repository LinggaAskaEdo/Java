package mls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by edo on 01/12/16.
 */

public class MLS_Substring
{
    public static void main(String[] args)
    {
        String result = subStringDate("/home/edo/Downloads/tempLookup/MLS-full-cell-export-2016-11-25T000000.csv");
        System.out.println(result);
    }

    private static String subStringDate(String orderList)
    {
        //return orderList.substring(orderList.indexOf("/MLS-full-cell-export-"), orderList.indexOf("T"));

        String result = null;

        Pattern pattern = Pattern.compile("cell-export-(.*?)T");
        Matcher matcher = pattern.matcher(orderList);

        while (matcher.find())
        {
            result = matcher.group(1);
        }

        return result;
    }
}