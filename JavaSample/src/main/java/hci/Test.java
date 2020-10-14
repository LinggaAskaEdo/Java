package hci;

import java.util.Arrays;

public class Test
{
    private static final String REL = "REL";
    private static final String REL_CC = "REL-CC";
    private static final String REL_PL = "REL-PL";
    private static final String CEL = "CEL";
    private static final String[] CONTRACT_TYPE = new String[] { REL, CEL };

    public static void main(String[] args)
    {
        String[] types = REL.split("-");
        System.out.println(types[0]);

        String[] types2 = REL_CC.split("-");
        System.out.println(types2[0]);
        System.out.println(types2[1]);
        System.out.println(Arrays.asList(CONTRACT_TYPE).contains(types2[0]));

        int a = 10;
        int b = 11;

        String c = a + "-" + b;

        System.out.println(c);
    }
}