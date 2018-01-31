package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;

public class KontrakDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public String getLastId()
    {
        String result = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MAX(KONTRAK_ID) as KONTRAK_ID FROM INVOICE_PROJECT.KONTRAK";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                Integer lastId = resultSet.getInt(1);

                result = String.valueOf(lastId);
            }

            resultSet.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close();
        }

        return result;
    }

    private void close()
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }

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