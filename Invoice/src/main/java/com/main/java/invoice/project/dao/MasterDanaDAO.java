package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.MasterDana;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;

/**
 * Created by dery on 2/11/18.
 */
public class MasterDanaDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void addUpdate(int id, String noBankAccount, String nameBankAccount, long totalCash, int flag)
    {
        try
        {
            String query = null;
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            if (flag == 0)
            {
                query = "INSERT INTO MASTER_DANA(" + id + ", '" + noBankAccount + "', '" + nameBankAccount + "', '" + totalCash + "')";
            }
            else
            {
                query = "UPDATE MASTER_DANA set NO_BANK_ACCOUNT = '" + noBankAccount + "', NAME_BANK_ACCOUNT = '" + nameBankAccount + "', TOTAL_CASH = '" + totalCash + "'" +
                        "WHERE MASTER_DANA_ID = " + id;
            }

            statement = connect.createStatement();
            statement.execute(query);

            statement.close();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close();
        }

    }

    public void getMasterDate () {

    }

    private void close()
    {
        try
        {
            if (statement != null)
            {
                statement.close();
            }

            if (connect != null)
            {
                connect.close();
            }
        }
        catch (Exception ignored)
        {}
    }
}
