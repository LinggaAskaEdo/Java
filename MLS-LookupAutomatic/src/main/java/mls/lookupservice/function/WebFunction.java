package mls.lookupservice.function;

import mls.lookupservice.model.CellDB;
import org.springframework.stereotype.Component;

/**
 * Created by Lingga on 23/03/17.
 */

@Component
public class WebFunction
{
    public static String CONSTANT_WCDMA = "wcdma";
    public static String CONSTANT_GSM = "gsm";
    public static String EMPTY_RESULT = "Can not find data with that cell ref";

    static String INVALID_MCC = "Invalid format for MCC";
    static String INVALID_NET = "Invalid format for NET";
    static String INVALID_AREA = "Invalid format for AREA";
    static String INVALID_CELL = "Invalid format for CELL";

    private String errorMessage = null;

    public String checkCellDB(CellDB cellDB)
    {
        int mcc = cellDB.getMcc();
        int net = cellDB.getNet();
        int area = cellDB.getArea();
        int cell = cellDB.getCell();

        if (mcc <= 0)
        {
            errorMessage = INVALID_MCC;
        }
        else if (net <= 0)
        {
            errorMessage = INVALID_NET;
        }
        else if (area <= 0)
        {
            errorMessage = INVALID_AREA;
        }
        else if (cell <= 0)
        {
            errorMessage = INVALID_CELL;
        }

        return errorMessage;
    }
}