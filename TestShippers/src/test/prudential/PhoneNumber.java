package test.prudential;

public class PhoneNumber
{
    public static void main(String[] args)
    {
        String result;

        String S = "00-44  48 5555 8361";
//        String S = "0 - 22 1985--324";
//        String S = "1  3";
//        String S = "555372654";

        String validS = S.replaceAll("\\s", "").replace("-", "");
        System.out.println("validS: " + validS);

        int lengthS = validS.length();
        System.out.println("lengthS: " + lengthS);

        if (lengthS < 3)
        {
            result = validS;
        }
        else
        {
            int divS = validS.length() / 3;
            System.out.println("divS: " + divS);

            int remainS = validS.length() - (divS * 3);
            System.out.println("remainS: " + remainS);

            StringBuilder builder = new StringBuilder(validS);
            int x = 0;
            int marker = 0;

            if (remainS == 0)
            {
                for (int i = 1; i < divS; i++)
                {
                    marker = i * 3 + x;
                    builder.insert(marker, '-');
                    x++;
                }
            }
            else if (remainS == 1)
            {
                for (int i = 1; i <= divS - remainS; i++)
                {
                    marker = i * 3 + x;
                    builder.insert(marker, '-');
                    x++;
                }

                builder.insert(marker + 3, '-');
            }
            else if (remainS == 2)
            {
                for (int i = 1; i <= divS; i++)
                {
                    builder.insert(i * 3 + x, '-');
                    x++;
                }
            }

            result = builder.toString();
        }

        System.out.println("result: " + result);
    }
}