package com.main.java.invoice.project.dao;

import java.sql.*;

public class UserDAO
{
    private String url;
    private String username;
    private String password;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public UserDAO(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public boolean checkUser(String user, String pass)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url + "user=" + username + "&password=" + password);

            preparedStatement = connect.prepareStatement("SELECT USER_NAME, USER_PASSWORD, IS_ADMIN FROM INVOICE_PROJECT.USER WHERE USER_NAME = '" + user + "' and USER_PASSWORD = '" + pass +"'");
            resultSet = preparedStatement.executeQuery();

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