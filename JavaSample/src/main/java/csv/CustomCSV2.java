package csv;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by edo on 03/01/17.
 */

public class CustomCSV2
{
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final char DEFAULT_SEPARATOR = ',';

    public static void main(String[] args)
    {
        read("common2.csv", "", ' ', '\"', '\\', '\n',
                false, true);
    }

    private static List<List<String>> read(String filePath, String encoding, char markerSeparator, char markerValue, char markerSkip, char markerEol,
                                           boolean enableWhiteLine, boolean enableRemoveSpace)
    {
        List<List<String>> result = new ArrayList<>();

        if (StringUtils.isBlank(encoding)) //set default encoding if empty
        {
            encoding = DEFAULT_ENCODING;
        }

        try
        {
            Scanner scanner = new Scanner(new File(filePath), encoding);

            while (scanner.hasNext())
            {
                String cvsLine = scanner.nextLine();

                List<String> line = parseLine(cvsLine, markerSeparator, markerValue, markerSkip, markerEol, enableRemoveSpace);

                if (!line.get(0).equalsIgnoreCase("")) //check empty string
                {
                    result.add(line);
                }
                else
                {
                    if (enableWhiteLine)
                    {
                        result.add(null);
                    }
                }
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    private static List<String> parseLine(String cvsLine, char markerSeparator, char markerValue, char markerSkip, char markerEol, boolean enableRemoveSpace)
    {
        List<String> result = new ArrayList<>();

        if (cvsLine == null) //return if empty
        {
            return result;
        }

        if (markerSeparator == ' ') //set default separator if empty
        {
            markerSeparator = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();

        boolean inQuotes = false;
        boolean inSkip = false;
        boolean startCollectChar = false;
        boolean markerSkipInColumn = false;
        boolean statusQuoteAndSkip = false;

        char[] chars = cvsLine.toCharArray();
        int lengthChars = chars.length;
        //System.out.println("lengthChars: " + lengthChars);

        //getIndexOfSeparator(chars, markerSeparator);

        for (int i = 0; i < chars.length; i++)
        {
            if (markerValue != ' ')
            {
                if (inQuotes)
                {
                    if (chars[i] == markerValue)
                    {

                    }
                    else
                    {
                        curVal.append(chars[i]);
                    }
                    //if
                }
                else
                {
                    if (chars[i] == markerValue || chars[i] == markerSkip)
                    {
                        inQuotes = true;
                    }
                    else if (chars[i] == markerSeparator)
                    {
                        if (enableRemoveSpace)
                        {
                            result.add(curVal.toString().replace(" ", ""));
                        }
                        else
                        {
                            result.add(curVal.toString());
                        }

                        curVal = new StringBuffer();
                        startCollectChar = false;
                    }
                    else if (chars[i] == markerEol)
                    {
                        break; //the end of line, break!
                    }
                }
            }
            else
            {
                if (chars[i] == markerSeparator)
                {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                }
                else
                {
                    curVal.append(chars[i]);
                }
            }
        }

        if (enableRemoveSpace)
        {
            result.add(curVal.toString().replace(" ", ""));
        }
        else
        {
            result.add(curVal.toString());
        }

        System.out.println("Result[0]: " + result.get(0));
        System.out.println("Result[1]: " + result.get(1));
        System.out.println("Result[2]: " + result.get(2));

        return result;
    }

    private static void getIndexOfSeparator(char[] chars, char markerSeparator)
    {
        //int indexOfSeparator = Arrays.asList(chars).indexOf(markerSeparator);
        //System.out.println("indexOfSeparator: " + new String(chars).indexOf(markerSeparator));

        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == markerSeparator)
            {
                System.out.println("index: " + i);
            }
        }
    }
}