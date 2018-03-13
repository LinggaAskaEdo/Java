package csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by edo on 03/01/17.
 */

public class SampleJavaScanner
{
    private static final String CHARSET_NAME = "UTF-8";
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static void main(String[] args)
    {
        try
        {
            Scanner scanner = new Scanner(new File("common2.csv"), CHARSET_NAME);

            while (scanner.hasNext())
            {
                List<String> line = parseLine(scanner.nextLine());
                if (!line.get(0).equalsIgnoreCase(""))
                    System.out.println("Country [id= " + line.get(0) + ", code= " + line.get(1) + " , name=" + line.get(2) + "]");
            }
            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static List<String> parseLine(String cvsLine)
    {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    private static List<String> parseLine(String cvsLine, char separators, char customQuote)
    {
        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null)
        {
            return result;
        }

        if (customQuote == ' ')
        {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ')
        {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars)
        {
            if (inQuotes)
            {
                startCollectChar = true;

                if (ch == customQuote)
                {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                }
                else
                {
                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"')
                    {
                        if (!doubleQuotesInColumn)
                        {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    }
                    else
                    {
                        curVal.append(ch);
                    }
                }
            }
            else
            {
                if (ch == customQuote)
                {
                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"')
                    {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar)
                    {
                        curVal.append('"');
                    }
                }
                else if (ch == separators)
                {
                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;
                }
                else if (ch == '\n')
                {
                    break;
                }
                else
                {
                    curVal.append(ch);
                }
            }
        }

        result.add(curVal.toString());

        return result;
    }
}