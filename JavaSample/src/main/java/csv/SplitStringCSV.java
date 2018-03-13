package csv;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by edo on 10/01/17.
 */

public class SplitStringCSV
{
    private static String RESULT_OK = "OK";
    private static String RESULT_ERROR = "ERROR";

    public static void main(String[] args)
    {
        boolean KEEP_GOING = true; //based on parameter input
        int lineNumber = 0; //based on parameter input
        List<ReportResultCsv> allReports = new ArrayList<>();

        try
        {
            Scanner scanner = new Scanner(new File("common2.csv"), "UTF-8");

            while (scanner.hasNext())
            {
                String csvLine = scanner.nextLine();
                ReportResultCsv reportResultCsv = splitString(csvLine, "UTF-8", ' ',',', ' ', '\n',
                        false, false);

                if (reportResultCsv.getData() != null)
                {
                    //System.out.println("TIDAK KOSONG");
                    for (int i = 0; i < reportResultCsv.getData().size(); i++)
                    {
                        System.out.println(reportResultCsv.getData().get(i));
                    }
                }
                /*else
                {
                    System.out.println("KOSONG");
                }*/

                if (reportResultCsv.getMessage().equalsIgnoreCase(RESULT_OK))
                {
                    //System.out.println("UUU1");
                    reportResultCsv.setMessage("Line " + lineNumber + ", status: " + RESULT_OK);
                }
                else
                {
                    //System.out.println("UUU2");
                    reportResultCsv.setMessage("Line " + lineNumber + ", status: " + RESULT_ERROR);
                }

                System.out.println(reportResultCsv.getMessage());

                allReports.add(reportResultCsv);

                if (!KEEP_GOING)
                {
                    //System.out.println("UUU3");
                    break; /*stop read file!*/
                }

                lineNumber++;
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static ReportResultCsv splitString(String csvLine, String encoding, char textIndicator, char textSeparator, char textEscape, char endOfLine,
                                               boolean isWhiteLine, boolean isRemoveSpace)
    {
        ReportResultCsv reportResultCsv = new ReportResultCsv();

        List<String> result = new ArrayList<>();

        String[] resultSplit;

        if ((csvLine.equalsIgnoreCase("")) && (!isWhiteLine))
        {
            //System.out.println("QQQ1");
            reportResultCsv.setData(null);
            reportResultCsv.setMessage(RESULT_OK);
            return reportResultCsv;
        }
        else
        {
            //System.out.println("QQQ2");
            resultSplit = csvLine.split(String.valueOf(textSeparator));
        }

        StringBuilder stumpValue = new StringBuilder();

        boolean isStump = false;

        //int lengthResultSplit = resultSplit.length;

        if (textIndicator == ' ') /*text indicator is empty*/
        {
            for (String rs : resultSplit)
            {
                if (rs.contains(String.valueOf(endOfLine)))
                {
                    rs = removeContain(rs, endOfLine, "");

                    if (isRemoveSpace)
                    {
                        rs = removeSpace(rs);
                        result.add(rs);
                    }
                    else
                    {
                        result.add(rs);
                    }

                    break;
                }
                else
                {
                    if (isRemoveSpace)
                    {
                        result.add(removeSpace(rs));
                    }
                    else
                    {
                        result.add(rs);
                    }
                }
            }
        }
        else /*text indicator is not empty*/
        {
            char ESCAPE_DOUBLE_QUOTE = '\"';
            char ESCAPE_SINGLE_QUOTE = '\'';
            char ESCAPE_BACK_SLASH = '\\';

            for (String rs : resultSplit)
            {
                //System.out.println(rs);

                if (isContainIndicator(rs, textIndicator)) /*if there's no text indicator, data is valid (add to result / temp)*/
                {
                    if (isStump)
                    {
                        //System.out.println("AAA1");
                        stumpValue.append(rs);
                        stumpValue.append(textSeparator);
                    }
                    else
                    {
                        if (rs.contains(String.valueOf(endOfLine)))
                        {
                            //System.out.println("AAA2");
                            rs = removeContain(rs, endOfLine, "");

                            if (((textEscape == ESCAPE_DOUBLE_QUOTE) || (textEscape == ESCAPE_SINGLE_QUOTE)) && rs.contains(String.valueOf(textEscape)))
                            {
                                if (checkEscapeQuote(rs, textEscape))
                                {
                                    rs = removeContain(rs, String.valueOf(textEscape) + String.valueOf(textEscape), textEscape);
                                }
                                else
                                {
                                    result = null;
                                    reportResultCsv.setData(result);
                                    reportResultCsv.setMessage(RESULT_ERROR);
                                    break;
                                }
                            }
                            else if (textEscape == ESCAPE_BACK_SLASH)
                            {
                                rs = checkEscapeBackslash(rs, ESCAPE_BACK_SLASH, encoding);
                            }

                            if (isRemoveSpace)
                            {
                                rs = removeSpace(rs);
                                result.add(rs);
                            }
                            else
                            {
                                result.add(rs);
                            }

                            break;
                        }
                        else
                        {
                            if (((textEscape == ESCAPE_DOUBLE_QUOTE) || (textEscape == ESCAPE_SINGLE_QUOTE)) && rs.contains(String.valueOf(textEscape)))
                            {
                                if (checkEscapeQuote(rs, textEscape))
                                {
                                    rs = removeContain(rs, String.valueOf(textEscape) + String.valueOf(textEscape), textEscape);
                                }
                                else
                                {
                                    result = null;
                                    reportResultCsv.setData(result);
                                    reportResultCsv.setMessage(RESULT_ERROR);
                                    break;
                                }
                            }
                            else if (textEscape == ESCAPE_BACK_SLASH)
                            {
                                rs = checkEscapeBackslash(rs, ESCAPE_BACK_SLASH, encoding);
                            }

                            //System.out.println("AAA3");
                            if (isRemoveSpace)
                            {
                                rs = removeSpace(rs);
                                result.add(rs);
                            }
                            else
                            {
                                result.add(rs);
                            }
                        }
                    }
                }
                else if (isFlankByIndicator(rs, textIndicator)) /*if there's text indicator at first and last string, data is valid (add to result)*/
                {
                    if (isStump) /*parse error because status true*/
                    {
                        //System.out.println("BBB1");
                        result = null;
                        reportResultCsv.setData(result);
                        reportResultCsv.setMessage(RESULT_ERROR);
                        break;
                    }
                    else
                    {
                        if (rs.contains(String.valueOf(endOfLine)))
                        {
                            //System.out.println("BBB2");
                            rs = removeContain(rs, endOfLine, "");
                            rs = removeContain(rs);

                            if (((textEscape == ESCAPE_DOUBLE_QUOTE) || (textEscape == ESCAPE_SINGLE_QUOTE)) && rs.contains(String.valueOf(textEscape)))
                            {
                                if (checkEscapeQuote(rs, textEscape))
                                {
                                    System.out.println("KUTIL");
                                    rs = removeContain(rs, String.valueOf(textEscape) + String.valueOf(textEscape), textEscape);
                                }
                                else
                                {
                                    System.out.println("PECAH");
                                    result = null;
                                    reportResultCsv.setData(result);
                                    reportResultCsv.setMessage(RESULT_ERROR);
                                    break;
                                }
                            }
                            else if (textEscape == ESCAPE_BACK_SLASH)
                            {
                                rs = checkEscapeBackslash(rs, ESCAPE_BACK_SLASH, encoding);
                            }

                            if (isRemoveSpace)
                            {
                                rs = removeSpace(rs);
                                result.add(rs);
                            }
                            else
                            {
                                result.add(rs);
                            }

                            break;
                        }
                        else
                        {
                            //System.out.println("BBB3");
                            rs = removeContain(rs);

                            if (((textEscape == ESCAPE_DOUBLE_QUOTE) || (textEscape == ESCAPE_SINGLE_QUOTE)) && rs.contains(String.valueOf(textEscape)))
                            {
                                if (checkEscapeQuote(rs, textEscape))
                                {
                                    rs = removeContain(rs, String.valueOf(textEscape) + String.valueOf(textEscape), textEscape);
                                }
                                else
                                {
                                    result = null;
                                    reportResultCsv.setData(result);
                                    reportResultCsv.setMessage(RESULT_ERROR);
                                    break;
                                }
                            }
                            else if (textEscape == ESCAPE_BACK_SLASH)
                            {
                                rs = checkEscapeBackslash(rs, ESCAPE_BACK_SLASH, encoding);
                            }

                            if (isRemoveSpace)
                            {
                                rs = removeSpace(rs);
                                result.add(rs);
                            }
                            else
                            {
                                result.add(rs);
                            }
                        }
                    }
                }
                else if (rs.startsWith(String.valueOf(textIndicator))) /*if there's text indicator at first string, data is valid (add to temp)*/
                {
                    if (isStump)
                    {
                        //System.out.println("CCC1");
                        result = null;
                        reportResultCsv.setData(result);
                        reportResultCsv.setMessage(RESULT_ERROR);
                        break;
                    }
                    else
                    {
                        if (rs.contains(String.valueOf(endOfLine))) /*parse error because start with text indicator only and have reach final line*/
                        {
                            //System.out.println("CCC2");
                            result = null;
                            reportResultCsv.setData(result);
                            reportResultCsv.setMessage(RESULT_ERROR);
                            break;
                        }
                        else
                        {
                            //System.out.println("CCC3");
                            stumpValue.append(rs);
                            stumpValue.append(textSeparator);
                            isStump = true;
                        }
                    }
                }
                else if (rs.endsWith(String.valueOf(textIndicator))) /*if there's text indicator at last string*/
                {
                    if (isStump)
                    {
                        //System.out.println("DDD1");
                        stumpValue.append(rs);

                        String stringStumpValue = stumpValue.toString();

                        if (rs.contains(String.valueOf(endOfLine)))
                        {
                            //System.out.println("DDD2");
                            stringStumpValue = removeContain(stringStumpValue, endOfLine, "");
                        }
                        else
                        {
                            //System.out.println("DDD3");
                            stringStumpValue = removeContain(stringStumpValue);
                        }

                        if (((textEscape == ESCAPE_DOUBLE_QUOTE) || (textEscape == ESCAPE_SINGLE_QUOTE)) && stringStumpValue.contains(String.valueOf(textEscape)))
                        {
                            if (checkEscapeQuote(stringStumpValue, textEscape))
                            {
                                stringStumpValue = removeContain(rs, String.valueOf(textEscape) + String.valueOf(textEscape), textEscape);
                            }
                            else
                            {
                                result = null;
                                reportResultCsv.setData(result);
                                reportResultCsv.setMessage(RESULT_ERROR);
                                break;
                            }
                        }
                        else if (textEscape == ESCAPE_BACK_SLASH)
                        {
                            stringStumpValue = checkEscapeBackslash(stringStumpValue, ESCAPE_BACK_SLASH, encoding);
                        }

                        if (isRemoveSpace)
                        {
                            stringStumpValue = removeSpace(stringStumpValue);
                            result.add(stringStumpValue);
                        }
                        else
                        {
                            result.add(stringStumpValue);
                        }

                        isStump = false;
                    }
                    else
                    {
                        //System.out.println("DDD4");
                        result = null;
                        reportResultCsv.setData(result);
                        reportResultCsv.setMessage(RESULT_ERROR);
                        break;
                    }
                }
            }
        }

        //if (reportResultCsv.getMessage().equalsIgnoreCase(RESULT_ERROR))
        if (reportResultCsv.getMessage() != null)
        {
            //System.out.println("XXX1");
            reportResultCsv.setData(null);
            reportResultCsv.setMessage(RESULT_ERROR);
        }
        else
        {
            //System.out.println("XXX2");
            reportResultCsv.setData(result);
            reportResultCsv.setMessage(RESULT_OK);
        }

        return reportResultCsv;
    }

    private static boolean isContainIndicator(String resultSplit, char textIndicator)
    {
        boolean status = false;

        if (resultSplit.indexOf(textIndicator) < 0)
            status = true;

        return status;
    }

    private static boolean isFlankByIndicator(String resultSplit, char textIndicator)
    {
        boolean status = false;

        if ((resultSplit.startsWith(String.valueOf(textIndicator))) && (resultSplit.endsWith(String.valueOf(textIndicator))))
            status = true;

        return status;
    }

    private static String removeContain(String rs) /*remove text indicator at first and last string*/
    {
        return rs.substring(1, rs.length() - 1);
    }

    private static String removeContain(String rs, String charContain, char finalChar) /*remove double or single quote*/
    {
        return rs.replace(charContain, String.valueOf(finalChar));
    }

    private static String removeContain(String rs, char charContain, String finalString) /*remove end of line*/
    {
        return rs.replace(String.valueOf(charContain), finalString);
    }

    private static String removeSpace(String rs)
    {
        return rs.replace(" ", "");
    }

    private static boolean checkEscapeQuote(String csvLine, char defaultQuote)
    {
        boolean status = false;

        int count = StringUtils.countMatches(csvLine, defaultQuote); /*count total quote in string*/

        if((count % 2) == 0) /*even*/
        {
            List<Integer> indexResult = new ArrayList<>();

            for (int i = -1; (i = csvLine.indexOf(defaultQuote, i + 1)) != -1;) /*indexing quote in string*/
            {
                indexResult.add(i);
            }

            int size = indexResult.size(); /*check position if close or not*/

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

    private static String checkEscapeBackslash(String csvLine, char backslash, String encoding)
    {
        return StringEscapeUtils.unescapeJava(groupByEscape(csvLine, backslash, encoding));
    }

    private static String groupByEscape(String csvLine, char backslash, String encoding)
    {
        StringBuilder temp = new StringBuilder();

        byte[] arrString = null;
        int arrLength = 0;

        try
        {
            arrString = csvLine.getBytes(encoding);
            arrLength = arrString.length;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < arrLength; i++)
        {
            //System.out.println("byte: " + arrString[i] + ", char: " + (char) arrString[i]);

            if ((char) arrString[i] == backslash)
            {
                if ((i + 1) < arrLength)
                {
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

        //System.out.println("result: " + temp.toString());

        return temp.toString();
    }

    private static boolean isSpecialCharacter(char specialChar)
    {
        boolean status = false;

        switch (specialChar)
        {
            case 'n':
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
}