package testonline;

import java.util.Scanner;

public class TestShipperPalindromes
{
    public static void main(String[] args)
    {
        String s1, s2;
        int l1, l2;
        Scanner s = new Scanner(System.in);
        s1 = s.next();
        s2 = s.next();
        l1 = s1.length();
        l2 = s2.length();

        int ans = Integer.MAX_VALUE;

        if (l2 > l1)
        {
            System.out.println(-1);

            System.exit(0);
        }

        for (int i = 0; i < l1 - l2 + 1; i++)
        {
            String temp = s1.substring(0, i) + s2 + s1.substring(i + l2);
            int cost = 0;

            for (int j = 1; j < i + l2; j++)
            {
                if (s1.charAt(j) != temp.charAt(j))
                {
                    cost++;
                }
            }

            int z = 0;
            for (int j = 0; j < Math.ceil(l1/2.0); j++)
            {
                if ((j < i || j >= i + l2) && temp.charAt(j) != temp.charAt(l1-j-1))
                {
                    cost++;
                }
                else if (temp.charAt(j) != temp.charAt(l1-j-1) && (l1-j-1 < i || l1-j-1 >= i+l2))
                {
                    cost++;
                }
                else if (temp.charAt(j) != temp.charAt(l1-j-1))
                {
                    z = 1;
                    break;
                }
            }

            if (z == 0)
                ans = Math.min(ans, cost);
        }

        if (ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);
    }
}
