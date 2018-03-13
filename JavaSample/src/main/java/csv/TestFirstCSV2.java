package csv;

import com.firstwap.altamides.v3.first.csv.*;
import com.firstwap.altamides.v3.first.csv.ReportResult;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by edo on 05/01/17.
 */

public class TestFirstCSV2
{
    private static final Logger logger = Logger.getLogger(TestFirstCSV2.class.getName());

    public static void main(String[] args)
    {
        try
        {
            FirstCsv firstCsv = new FirstCsv();

            List<ReportResult> reportResults = firstCsv.read("test_space.csv", "", ' ', '\"', '\"', '\n',
                    false, false, true);

            for (ReportResult aResult : reportResults)
            {
                for (String resultData: aResult.getData())
                {
                    System.out.println("Data:" + resultData);
                }
                System.out.println("Message:" + aResult.getMessage());
            }
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Exception: ", e);
        }
    }
}