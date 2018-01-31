package mls.lookupservice.dao;

import mls.lookupservice.model.CellDB;

import java.util.List;

/**
 * Created by Lingga on 23/03/17.
 */

public interface MlsDao
{
    int checkCellDB();
    boolean batchProcess(String filePath);
    boolean batchFullProcess(String filePath);
    boolean truncateTable();
    String readCounterDao();
    void updateCounterDao(String counterValue);
    String readMarkerDao();
    void updateMarkerDao(String markerValue);
    List<CellDB> getLocation(String radio, int mcc, int net, int area, int cell);
}