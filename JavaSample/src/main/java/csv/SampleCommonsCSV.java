package csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by edo on 03/01/17.
 */

public class SampleCommonsCSV
{
    public static void main(String[] args) throws IOException
    {
        //final URL resources = getClass().getClassLoader().getResource("csvFile.csv");
        Reader in = new FileReader("common.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records)
        {
            String lastName = record.get(0);
            String firstName = record.get(1);
            System.out.print(lastName + ", " + firstName);
        }
    }
}