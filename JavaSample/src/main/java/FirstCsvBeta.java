import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lingga on 05/01/17.
 */

public class FirstCsvBeta
{
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_VALUE_SINGLE_QUOTE = '\'';
    private static final char DEFAULT_VALUE_DOUBLE_QUOTE = '\"';

    public List<List<String>> read(String filePath, String encoding, char markerSeparator, char markerValue, char markerSkip, char markerEol,
                                   boolean enableWhiteLine, boolean enableRemoveSpace)
    {
        List<List<String>> result = new ArrayList<>();

        if (StringUtils.isBlank(encoding))
        {
            encoding = DEFAULT_ENCODING;
        }

        try
        {
            Scanner scanner = new Scanner(new File(filePath), encoding);

            while (scanner.hasNext())
            {
                String cvsLine = scanner.nextLine();

                List<String> line = parseLine(cvsLine, encoding, markerSeparator, markerValue, markerSkip, markerEol, enableRemoveSpace);

                //check empty string
                if (!line.get(0).equalsIgnoreCase(""))
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

    public List<List<String>> read(String filePath, String encoding, char markerSeparator, char markerValue, char markerSkip, char markerEol,
                                   boolean enableWhiteLine, boolean enableRemoveSpace, int lineStart, int lineEnd)
    {
        int lineNum = 0;

        List<List<String>> result = new ArrayList<>();

        if (StringUtils.isBlank(encoding))
        {
            encoding = DEFAULT_ENCODING;
        }

        try
        {
            Scanner scanner = new Scanner(new File(filePath), encoding);

            while (scanner.hasNext())
            {
                String cvsLine = scanner.nextLine();

                if (lineStart <= lineNum && lineNum <= lineEnd)
                {
                    List<String> line = parseLine(cvsLine, encoding, markerSeparator, markerValue, markerSkip, markerEol, enableRemoveSpace);

                    //check empty string
                    if (!line.get(0).equalsIgnoreCase(""))
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

                lineNum++;
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    private List<String> parseLine(String cvsLine, String encoding, char markerSeparator, char markerValue, char markerSkip, char markerEol, boolean enableRemoveSpace)
    {
        List<String> result = new ArrayList<>();

        //Return if empty
        if (cvsLine == null)
        {
            return result;
        }

        //Set default separator if empty
        if (markerSeparator == ' ')
        {
            markerSeparator = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();

        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;
        boolean statusQuoteAndSkip = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars)
        {
            if (inQuotes)
            {
                startCollectChar = true;

                if ((ch == markerValue) || (ch == markerSkip))
                {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                    statusQuoteAndSkip = true;
                }
                else
                {
                    if (ch == markerValue)
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
                if ((ch == markerValue) || (ch == markerSkip))
                {
                    inQuotes = true;

                    if ((chars[0] != markerValue))
                    {
                        curVal.append(markerValue);
                    }

                    if (startCollectChar)
                    {
                        curVal.append(ch);
                    }
                }
                else if (ch == markerSeparator)
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
                else if (ch == markerEol)
                {
                    break;
                }
                else if (markerValue == ' ')
                {
                    if ((chars[0] == DEFAULT_VALUE_SINGLE_QUOTE) || (chars[0] == DEFAULT_VALUE_DOUBLE_QUOTE))
                    {
                        break;
                    }
                    else
                    {
                        curVal.append(ch);
                    }
                }
                else if ((markerSkip != ' ') && statusQuoteAndSkip)
                {
                    curVal.append(ch);
                    startCollectChar = false;
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

        return result;
    }
}