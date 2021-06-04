package string;

import java.util.Arrays;

public class CheckArrayContains
{
    private static final String[] OFFER_TYPE = new String[] { "PLT", "PLX" };
    private static final String[] ENTRY_POINT = new String[] { "APPLICATION_CREATION_2SP", "SCORING" };
    private static final String[] WHITELIST = new String[] { "PP_SAI", "ONLINE_DP", "CRITICAL"};

    public static void main(String[] args)
    {
        if (Arrays.asList(WHITELIST).contains("CRITICAL_1"))
        {
            System.out.println("AAA");
        }
        else
        {
            System.out.println("BBB");
        }

        if ("Home Credit Paylater Xsell A3".toLowerCase().contains("payLater".toLowerCase()))
            System.out.println("AAA2");
        else
            System.out.println("BBB2");
    }
}