import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by edo on 13/12/16.
 */

public class SampleSql20
{
    private static Sql2o sql2o;

    static
    {
        sql2o = new Sql2o("jdbc:mysql://localhost:3306/MLS_CELL_LOOKUP", "root", "");
    }

    public static void main(String[] args) throws SQLException
    {
        System.out.println("Result: " + checkStatus());
        if (checkStatus())
        {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/CELL_DB", "root", "");
            System.out.println("A: " + getResult() + "sql20: " + sql2o.getDataSource().getConnection().getCatalog());
        }
        else
        {
            System.out.println("B: " + getResult() + "sql20: " + sql2o.getDataSource().getConnection().getCatalog());
        }
    }

    private static boolean checkStatus()
    {
        boolean status = false;
        String sql = "SELECT STATUS_VALUE FROM STATUS";

        try (Connection connection = sql2o.open())
        {
            status = connection.createQuery(sql).executeAndFetchFirst(Boolean.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return status;
    }

    private static List<String> getResult()
    {
        List<String> result = null;
        String sql = "SELECT MCC FROM CELL_DB LIMIT 3";

        try (Connection connection = sql2o.open())
        {
            result = connection.createQuery(sql).executeScalarList(String.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }
}