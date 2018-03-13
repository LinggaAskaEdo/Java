package csv;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by edo on 03/01/17.
 */

public class SampleOpenCSV
{
    //private static boolean ENABLE_BLANK_SPACE = false;
    //private static char DEFAULT_MARKER_VALUE = ' ';

    public static void main(String[] args)
    {
        String filePath = "common2.csv";
        String encoding = "UTF-8";
        char markerSeparator = ',';
        char markerValue = '`';
        char markerSkip = '\\';
        char markerEol = '\n';

        read(filePath, encoding, markerSeparator, markerValue, markerSkip, markerEol, false, true);
    }

    private static void read(String filePath, String encoding, char markerSeparator, char markerValue, char markerSkip, char markerEol,
                             boolean enableWhiteLine, boolean enableRemoveSpace)
    {
        try
        {
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), encoding), markerSeparator, markerValue);
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null)
            {
                //System.out.println(nextLine);
                //System.out.println(nextLine[0] + " - " + nextLine[1] + " - " + nextLine[2]);

                if (StringUtils.isBlank(nextLine[0]))
                {
                    if (enableWhiteLine)
                    {
                        System.out.println("NULL");
                    }
                }
                else
                {
                    //System.out.println("NOT NULL");
                    checkLineCSV(nextLine, markerValue);
                    //System.out.println(nextLine[0] + " - " + nextLine[1] + " - " + nextLine[2]);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }

    private static void checkLineCSV(String[] nextLine, char defaultMarkerValue)
    {
        //System.out.println("Length column: " + nextLine.length);

        boolean status = false;

        if (defaultMarkerValue != ' ')
        {
            for (String aNextLine : nextLine)
            {
                if (isContainsMarkerValue(aNextLine, defaultMarkerValue))
                {
                    break;
                }
            }

            //
        }
        else
        {
            System.out.println(nextLine[0] + " - " + nextLine[1] + " - " + nextLine[2]);
        }
    }

    private static boolean isContainsMarkerValue(String aNextLine, char defaultMarkerValue)
    {
        boolean status = false;

        if ((aNextLine.charAt(0) == defaultMarkerValue) && (aNextLine.charAt(aNextLine.length() - 1) == defaultMarkerValue))
        {
            status = true;
        }

        return status;
    }
}