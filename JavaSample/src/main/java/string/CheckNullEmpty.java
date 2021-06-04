package string;

import org.apache.commons.lang3.StringUtils;

public class CheckNullEmpty
{
    public static void main(String[] args)
    {
        String xxx = null;

        System.out.println(StringUtils.isEmpty(xxx));

        boolean yyy = !StringUtils.isEmpty(xxx);
        System.out.println(yyy ? xxx : "-");
    }
}