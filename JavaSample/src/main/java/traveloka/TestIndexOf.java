package traveloka;

import java.util.Arrays;

import static java.lang.System.*;

public class TestIndexOf
{
    public static void main(String[] args)
    {
        String word = "traveloka";
        String locate = "rans";

        out.println(findIndex(word, locate));
    }

    private static int findIndex(String word, String locate)
    {
        int result = -1;
        int i = 0;
        int j = 0;
        boolean status = false;
        boolean loop = true;

        char[] charWord = word.toCharArray();
        char[] charLocate = locate.toCharArray();
        char[] charResult = new char[charLocate.length];

        while (loop)
        {
            out.println("'" + charWord[i] + "', '" + charLocate[j] + ";");

            if (charWord[i] == charLocate[j])
            {
                if (!status)
                {
                    result = i;
                    status = true;
                }

                charResult[j] = charLocate[j];

                if (charLocate.length - 1 > j)
                {
                    j++;
                }
                else
                {
                    loop = false;
                }
            }
            else
            {
                if (status)
                {
                    result = -1;
                    loop = false;
                }
            }

            i++;
        }

        out.println(Arrays.toString(charResult));

        if (!Arrays.toString(charLocate).equalsIgnoreCase(Arrays.toString(charResult)))
        {
            result = -1;
        }

        return result;
    }
}