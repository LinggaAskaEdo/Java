package csv;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dery on 11/01/17.
 */

public class textIndicatorEscape
{
    private static final char DEFAULT_QUOTE = '"';
    private static final char DEFAULT_BACKSLASH = '\\';

    public static void main(String[] args)
    {
        try
        {
            Scanner scanner = new Scanner(new File("commonFile2.csv"), "UTF-8");

            while (scanner.hasNext())
            {
                String csvLine = scanner.nextLine();
                System.out.println(csvLine);

                //System.out.println(checkEscapeQuote(csvLine, DEFAULT_QUOTE));
                System.out.println("Result: " + checkEscapeBackslash(csvLine, DEFAULT_BACKSLASH, DEFAULT_QUOTE));
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static String groupByEscape(String csvLine, char backslash)
    {
        StringBuilder temp = new StringBuilder();

        /*process with byte*/
        byte[] arrString;

        arrString = csvLine.getBytes();
        int arrLength = arrString.length;

        for (int i = 0; i < arrLength; i++)
        {
            //System.out.println("byte: " + arrString[i] + ", char: " + (char) arrString[i]);

            if ((char) arrString[i] == backslash)
            {
                if ((i + 1) < arrLength)
                {
                    //if ((char) arrString[i + 1] == 't')
                    if (isSpecialCharacter((char) arrString[i + 1]))
                    {
                        temp.append((char) arrString[i]);
                    }

                    temp.append((char) arrString[i + 1]);

                    i++;
                }
                else
                {
                    temp.append((char) arrString[i]);
                }
            }
            else
            {
                temp.append((char) arrString[i]);
            }
        }

        System.out.println("result: " + temp.toString());
        /*end process*/

        //char[] chars = csvLine.toCharArray();

        //System.out.println("Length: " + chars.length);

        //csvLine = csvLine.replace("\\", "\\\\");

        /*for (int i = 0; i <= csvLine.length(); i++)
        {
            System.out.println("Extract: " + csvLine.charAt(i));
        }*/

        /*for(int i = 0; i <= csvLine.length() - 1; i++)
        {
            System.out.println("Extract: " + csvLine.charAt(i));
        }*/

        /*for (int i = 0; i <= chars.length - 1; i++)
        {
            System.out.println("Ori: " + chars[i]);

            if (chars[i] == '\\')
            {
                System.out.println("MEMEollosdK: " + chars[i] + ", i: "+ i);
                //if (((i + 1) < chars.length) && (i != chars.length - 1))
                if ((i + 1) < chars.length - 1)
                {
                    System.out.println("WOY: " + chars[i]);
                    temp.append(chars[i+1]);
                    i++;
                }

                if (i == (chars.length + 1))
                {
                    System.out.println("YUHUUU: " + i);
                    temp.append(chars[i+1]);
                }
            }
            else
            {
                temp.append(chars[i]);
            }
        }*/

        return temp.toString();
    }

    private static boolean isSpecialCharacter(char specialChar)
    {
        boolean status = false;

        switch (specialChar)
        {
            case '\n':
                status = true;
                break;
            case 't':
                status = true;
                break;
            case 'r':
                status = true;
                break;
            case '0':
                status = true;
                break;
            case 'b':
                status = true;
                break;
            case 'f':
                status = true;
                break;
            default:
                break;
        }

        return status;
    }

    private static String checkEscapeBackslash(String csvLine, char backslash, char quote)
    {
        groupByEscape(csvLine, backslash);
        /*String result = StringEscapeUtils.unescapeJava(csvLine);
        return groupByEscape(result, backslash);*/
        return null;
    }

    private static boolean checkEscapeQuote(String csvLine, char defaultQuote)
    {
        boolean status = false;

        /*count total quote in string*/
        int count = StringUtils.countMatches(csvLine, defaultQuote);
//        System.out.println(count);

        if((count % 2) == 0) /*even*/
        {
            List<Integer> indexResult = new ArrayList<>();

            /*indexing quote in string*/
            for (int i = -1; (i = csvLine.indexOf(defaultQuote, i + 1)) != -1; )
            {
//                System.out.println(i);
                indexResult.add(i);
            }

            /*check position if close or not*/
//            System.out.println(indexResult.size());
            int size = indexResult.size();

            if (size > 2)
            {
                boolean status2 = false;

                for (int i = 0; i < size; i = i + 2)
                {
                    status2 = indexResult.get(i + 1) - indexResult.get(i) == 1;

                    if (!status2)
                    {
                        break;
                    }
                }
                status = status2;
            }
            else if (size == 2)
            {
                status = indexResult.get(1) - indexResult.get(0) == 1;
            }
        }
        else /*odd*/
        {
            return false;
        }

        return status;
    }
}
