package string;

import org.apache.commons.lang3.StringUtils;

public class TestContains
{
    public static void main(String[] args)
    {
        String xxx = "JsT-nirmala-085715025257";

        System.out.println(StringUtils.containsIgnoreCase("GUEST", "guest"));

        System.out.println(StringUtils.containsIgnoreCase(xxx, "jst"));

        System.out.println(funcCheck(xxx));

        /*if (xxx.contains("jst"))
            System.out.println(true);
        else
            System.out.println(false);*/
    }

    private static String funcCheck(String xxx)
    {
        try
        {
            if (StringUtils.containsIgnoreCase(xxx, "jst"))
            {
                String[] nameWithJstFormat = xxx.split("-");

                if (nameWithJstFormat.length == 3 && nameWithJstFormat[0].equalsIgnoreCase("JST"))
                {
                    System.out.println(nameWithJstFormat[1]);
                    System.out.println(nameWithJstFormat[2]);
                    return "AAA";
                }
                else
                {
                    return "BBB";
                }
            }
            else
            {
                return "DDD";
            }
        }
        catch (Exception e)
        {
            return "CCC";
        }
    }
}