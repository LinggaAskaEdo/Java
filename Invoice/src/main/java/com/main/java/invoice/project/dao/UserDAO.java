package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.User;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void addUpdate(User user, int flag)
    {
        String query;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            System.out.println("User: " + user);

            if (flag == 0)
            {
                query = "INSERT INTO USER(USERS, USER_NAME, USER_PASSWORD, IS_ADMIN) VALUES (?, ?, ?, ?)";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, user.getUsers());
                preparedStatement.setString(2, user.getUserName());
                preparedStatement.setString(3, user.getUserPassword());
                preparedStatement.setInt(4, user.getIsAdmin());
            }
            else
            {
                query = "UPDATE USER set USER_NAME = ?, USER_PASSWORD = ?, IS_ADMIN = ? WHERE USERS = ?";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getUserPassword());
                preparedStatement.setInt(3, user.getIsAdmin());
                preparedStatement.setString(4, user.getUsers());
            }

            preparedStatement.executeUpdate();
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

    public List<User> GetAllUser ()
    {
        List<User> allUser = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT USER_ID, USERS, USER_NAME, USER_PASSWORD, IS_ADMIN FROM USER";

            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                User user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setUsers(resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setUserPassword(resultSet.getString(4));
                user.setIsAdmin(resultSet.getInt(5));

                allUser.add(user);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close();
        }

        return allUser;
    }

    public void DeleteUserById(User user)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "DELETE FROM USER WHERE USERS = ?";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, user.getUsers());

            preparedStatement.executeUpdate();
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