package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;

public class UserDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public boolean checkUser(String user, String pass)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT USER_NAME, USER_PASSWORD, IS_ADMIN FROM INVOICE_PROJECT.USER WHERE USER_NAME = '" + user + "' and USER_PASSWORD = '" + pass +"'";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                result = true;
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