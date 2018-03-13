package csv;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by edo on 05/01/17.
 */

public class SampleUnivocityCSV
{

    public static void main(String[] args)
    {
        CsvParserSettings settings = new CsvParserSettings();
        //the file used in the example uses '\n' as the line separator sequence.
        //the line separator sequence is defined here to ensure systems such as MacOS and Windows
        //are able to process this file correctly (MacOS uses '\r'; and Windows uses '\r\n').
        settings.getFormat().setLineSeparator("\n");

        // creates a CSV parser
        CsvParser parser = new CsvParser(settings);

        // parses all rows in one go.
        try
        {
            List<String[]> allRows = parser.parseAll(getFileReader("common2.csv"));

            for (String[] allRow : allRows)
                System.out.println(Arrays.toString(allRow));
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    private static Reader getFileReader(String absolutePath) throws FileNotFoundException, UnsupportedEncodingException
    {
        return new InputStreamReader(new FileInputStream(new File(absolutePath)), "UTF-8");
    }
}