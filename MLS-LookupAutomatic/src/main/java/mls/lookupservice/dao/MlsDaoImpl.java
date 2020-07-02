package mls.lookupservice.dao;

import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import mls.lookupservice.model.CellDB;
import mls.lookupservice.preference.Preference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by Lingga on 23/03/17.
 */

@Repository
public class MlsDaoImpl implements MlsDao
{
    private final Logger log = LoggerFactory.getLogger(MlsDaoImpl.class);

    private Sql2o sql2oPrimary;
    private Sql2o sql2oSecondary;
    private ElSql bundle;

    @Autowired
    public MlsDaoImpl(@Qualifier("sql2oPrimary") Sql2o sql2oPrimary, @Qualifier("sql2oSecondary") Sql2o sql2oSecondary)
    {
        this.sql2oPrimary = sql2oPrimary;
        this.sql2oSecondary = sql2oSecondary;
        this.bundle = ElSql.of(ElSqlConfig.MYSQL, MlsDaoImpl.class);
    }

    @Override
    public int checkCellDB()
    {
        int result = 0;

        String sql = bundle.getSql("CheckCellDB");
        log.info("checkCellDB: {}", sql);

        try (Connection connection = sql2oPrimary.open(); Query query = connection.createQuery(sql))
        {
            result = query.executeAndFetchFirst(Integer.class);
        }
        catch (Exception e)
        {
            log.error("Error readCounterDao: {}", e.getMessage());
        }

        return result;
    }

    @Override
    public boolean batchProcess(String filePath)
    {
        boolean status = false;

        /*start transaction for dump file*/
        String sql1 = bundle.getSql("CreateTemporary");
        log.info("createTemporary: {}", sql1);

        String sql2 = bundle.getSql("LoadDataFileTemporary");
        log.info("loadDataFile: {}", sql2);

        String sql3 = bundle.getSql("DumpFile");
        log.info("dumpFile: {}", sql3);

        String sql4 = bundle.getSql("DropTemporary");
        log.info("dropTemporary: {}", sql4);
        /*end transaction for dump file*/

        try (Connection con = sql2oPrimary.beginTransaction(); Query query1 = con.createQuery(sql1);
             Query query2 = con.createQuery(sql2);
             Query query3 = con.createQuery(sql3);
             Query query4 = con.createQuery(sql4))
        {
            query1.executeUpdate();
            query2.addParameter("filePath", filePath).executeUpdate();
            query3.executeUpdate();
            query4.executeUpdate();
            con.commit();

            status = true;
        }
        catch (Exception e)
        {
            log.error("Error batchProcess: {}", e.getMessage());
        }

        return status;
    }

    @Override
    public boolean batchFullProcess(String filePath)
    {
        boolean status = false;

        /*start transaction for dump file*/
        String sql1 = bundle.getSql("SetAutoCommit");
        log.info("setAutoCommit: {}", sql1);

        String sql2 = bundle.getSql("SetUniqueChecks");
        log.info("setUniqueChecks: {}", sql2);

        String sql3 = bundle.getSql("SetForeignKeyChecks");
        log.info("setForeignKeyChecks: {}", sql3);

        String sql5 = bundle.getSql("LoadDataFile");
        log.info("loadDataFile: {}", sql5);
        /*end transaction for dump file*/

        try (Connection con = sql2oPrimary.beginTransaction(); Query query1 = con.createQuery(sql1);
             Query query2 = con.createQuery(sql2);
             Query query3 = con.createQuery(sql3);
             Query query5 = con.createQuery(sql5))
        {
            query1.executeUpdate();
            query2.executeUpdate();
            query3.executeUpdate();
            query5.addParameter("filePath", filePath).executeUpdate();
            con.commit();

            status = true;
        }
        catch (Exception e)
        {
            log.error("Error batchProcess: {}", e.getMessage());
        }

        return status;
    }

    @Override
    public boolean truncateTable()
    {
        boolean status = false;

        String sql = bundle.getSql("TruncateTable");
        log.info("truncateTable: {}", sql);

    try (Connection connection = sql2oPrimary.open(); Query query = connection.createQuery(sql))
        {
            query.executeUpdate();
            status = true;
        }
        catch (Exception e)
        {
            log.error("Error truncateTable: {}", e.getMessage());
        }

        return status;
    }

    @Override
    public String readCounterDao()
    {
        String result = null;

        String sql = bundle.getSql("ReadCounter");
        log.info("readCounterDao: {}", sql);

        try (Connection connection = sql2oPrimary.open(); Query query = connection.createQuery(sql))
        {
            result = query.executeAndFetchFirst(String.class);
        }
        catch (Exception e)
        {
            log.error("Error readCounterDao: {}", e.getMessage());
        }

        return result;
    }

    @Override
    public void updateCounterDao(String counterValue)
    {
        String sql = bundle.getSql("UpdateCounter");
        log.info("updateCounterDao: {}", sql);

        try (Connection connection = sql2oPrimary.open(); Query query = connection.createQuery(sql))
        {
            query.addParameter("counterValue", counterValue).executeUpdate();
        }
        catch (Exception e)
        {
            log.error("Error updateCounterDao: {}", e.getMessage());
        }
    }

    @Override
    public String readMarkerDao()
    {
        String result = null;

        String sql = bundle.getSql("ReadMarker");
        log.info("readMarkerDao: {}", sql);

        try (Connection connection = sql2oPrimary.open(); Query query = connection.createQuery(sql))
        {
            result = query.executeAndFetchFirst(String.class);
        }
        catch (Exception e)
        {
            log.error("Error readMarkerDao: {}", e.getMessage());
        }

        return result;
    }

    @Override
    public void updateMarkerDao(String markerValue)
    {
        String sql = bundle.getSql("UpdateMarker");
        log.info("updateMarkerDao: {}", sql);

        try (Connection connection = sql2oPrimary.open(); Query query = connection.createQuery(sql))
        {
            query.addParameter("markerValue", markerValue).executeUpdate();
        }
        catch (Exception e)
        {
            log.error("Error updateMarkerDao: {}", e.getMessage());
        }
    }

    @Override
    public List<CellDB> getLocation(String radio, int mcc, int net, int area, int cell)
    {
        List<CellDB> result = null;

        String sql = bundle.getSql("GetLocation");
        log.info("getLocation: {}", sql);

        Sql2o sql2oTemp;

        if (Preference.STATUS_TABLE.equalsIgnoreCase(Preference.CELL_DB_STATUS_DISABLE))
        {
            sql2oTemp = sql2oSecondary;
        }
        else
        {
            sql2oTemp = sql2oPrimary;
        }

        try (Connection connection = sql2oTemp.open(); Query query = connection.createQuery(sql))
        {
            result = query
                    .addParameter("radio", radio)
                    .addParameter("mcc", mcc)
                    .addParameter("net", net)
                    .addParameter("area", area)
                    .addParameter("cell", cell)
                    .executeAndFetch(CellDB.class);
        }
        catch (Exception e)
        {
            log.error("Error getLocation: {}", e.getMessage());
        }

        return result;
    }
}