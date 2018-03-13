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

public class CustomCSV
{
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final char DEFAULT_SEPARATOR = ',';

    public static void main(String[] args)
    {
        read("common2.csv", "", ' ', ' ', ' ', '\n',
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
        //boolean inSkip = false;
        boolean startCollectChar = false;
        boolean markerSkipInColumn = false;
        //boolean statusQuoteAndSkip = false;

        char[] chars = cvsLine.toCharArray();
        int lengthChars = chars.length;
        //System.out.println("lengthChars: " + lengthChars);

        getIndexOfSeparator(chars, markerSeparator);

        for (int i = 0; i < lengthChars; i++)
        {
            if (markerValue != ' ')
            {
                if (inQuotes)
                {
                    startCollectChar = true;

                    if (chars[i] == markerValue || chars[i] == markerSkip)
                    {
                        inQuotes = false;
                        markerSkipInColumn = false;
                        //statusQuoteAndSkip = true;

                        /*System.out.println("i: " + i);
                        if (!(i >= lengthChars - 1))
                        {
                            System.out.println("AAA");
                            if ((chars[i + 1] == markerSkip) && (chars[i + 1] != markerSeparator))
                            {
                                inQuotes = false;
                                markerSkipInColumn = false;
                            }
                            else
                            {
                                curVal.append(chars[i]);
                            }
                        }*/
                    }
                    else
                    {
                        if (chars[i] == markerValue)
                        {
                            if (!markerSkipInColumn)
                            {
                                curVal.append(chars[i]);
                                markerSkipInColumn = true;
                            }
                        }
                        else
                        {
                            curVal.append(chars[i]); //A - U - S
                        }
                    }
                }
                else
                {
                    if (chars[i] == markerValue || chars[i] == markerSkip)
                    {
                        inQuotes = true;

                        /*if (chars[i] == '\\')
                        {
                            System.out.println("chars[i]: " + chars[i]);
                            System.out.println("chars[i + 1]: " + chars[i + 1]);
                        }*/

                        /*if (chars[0] != markerValue) //Fixed : allow "" in empty quote enclosed
                        {
                            //System.out.println("ZZZZ");
                            curVal.append(markerValue);
                        }*/

                        if (startCollectChar) //marker skip in column will hit this!
                        {
                            //System.out.println("AAA");
                            //System.out.println("BBB: " + chars[i + 1]);
                            curVal.append(chars[i]);

                            /*if ((chars[i + 1] == markerValue) || (chars[i + 1] == markerSkip) && (i <= lengthChars))
                            {
                                inQuotes = false;
                            }
                            else
                            {
                                inQuotes = true;
                            }*/

                            //inQuotes = !((chars[i + 1] == markerValue) || (chars[i + 1] == markerSkip) && (i <= lengthChars));
                        }
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
                    else
                    {
                        //if char i-1 equal marker value then append
                        if ((i != 0) && (chars[i - 1] == markerValue))
                        {
                            //System.out.println("AAA");
                            curVal.append(chars[i - 1]);
                            curVal.append(chars[i]);
                        }
                        //if char not equal marker (after separator) value while marker value not empty then append empty char
                        else if ((i != 0) && (chars[i - 1] == markerSeparator))
                        {
                            curVal.append(' ');

                            //append rest with empty char until next separator
                            //or set i (pointer to next separator index)
                            if (!(checkIndex(chars, markerSeparator, i) == -1))
                            {
                                i = checkIndex(chars, markerSeparator, i) - 1;
                                //System.out.println("BBB: " + i);
                            }
                            else
                            {
                                i = lengthChars;
                            }
                        }
                        //if first char not equal marker value while marker value not empty then append empty char
                        else if ((i == 0) && (markerValue != ' '))
                        {
                            curVal.append(' ');

                            //append rest with empty char until next separator
                            //or set i (pointer to next separator index)
                            if (!(checkIndex(chars, markerSeparator, i) == -1))
                            {
                                i = checkIndex(chars, markerSeparator, i) - 1;
                                //System.out.println("CCC: " + i);
                            }
                            else
                            {
                                i = lengthChars;
                            }
                        }
                        else
                        {
                            //System.out.println("DDD: " + chars[i]);
                            curVal.append(chars[i]);
                        }

                        //int indexSeparator = checkIndex(chars, markerSeparator, i);

                        //if ((i < resultChecking) )
                        //else if () //add checking for next char, if first char on their path doesn't have marker value while marker value not empty

                        /*if ((i == 0) && (chars[i - 1] == markerSeparator))
                        {
                            curVal.append(' ');
                        }
                        else
                        {
                            curVal.append(chars[i]);
                        }*/

                        /*if (markerValue != ' ')
                        {
                            //curVal.append(' ');
                            curVal.append(chars[i]);
                        }
                        else
                        {
                            curVal.append(' ');
                            //curVal.append(chars[i]);
                        }*/
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
        System.out.println("Result[2]: " + result.get(3));
        System.out.println("Result[2]: " + result.get(4));

        return result;
    }

    private static List<Integer> getIndexOfSeparator(char[] chars, char markerSeparator)
    {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == markerSeparator)
            {
                result.add(i);
            }
        }

        return result;
    }

    private static int checkIndex(char[] chars, char markerSeparator, int index)
    {
        int result = -1;

        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == markerSeparator)
            {
                if (index < i)
                {
                    result = i;
                    break;
                }
            }
        }

        return result;
    }
}