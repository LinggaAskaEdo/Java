package csv;

import com.firstwap.altamides.v3.first.csv.FirstCsv;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by edo on 05/01/17.
 */

public class TestFirstCSV
{
    private static final Logger logger = Logger.getLogger(TestFirstCSV.class.getName());

    public static void main(String[] args)
    {
        try
        {
            FirstCsv firstCsv = new FirstCsv();

            /*List<List<String>> result = firstCsv.read("common.csv", "", ' ', '\"', '\\', '\n',
                    false, true);

            for (List<String> aResult : result)
            {
                for (String anAResult : aResult)
                {
                    System.out.println(anAResult);
                }
            }*/
        }
        catch (Exception e)
        {
            //System.out.println(e.getStackTrace());
            logger.log(Level.SEVERE, "Exception: ", e);
        }
    }
}