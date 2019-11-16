package com.pdf.parser.dao;

import com.pdf.parser.preference.StaticPreference;

import java.sql.*;

public class ParserDAO
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT ID FROM PDF_PARSER.USER WHERE USER_NAME = '" + user + "' and USER_PASSWORD = '" + pass +"'";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                result = true;
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error when checkUser: " + e.getMessage());
        }
        finally
        {
            close();
        }

        return result;
    }

    public boolean checkContent(String noPengajuan)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT ID FROM PDF_PARSER.CONTENT WHERE NO_PENGAJUAN = '" + noPengajuan + "'";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                result = true;
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error when checkContent: " + e.getMessage());
        }
        finally
        {
            close();
        }

        return result;
    }

    public boolean insertContent(String noPengajuan, String body)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "INSERT INTO PDF_PARSER.CONTENT(NO_PENGAJUAN, BODY_CONTENT) VALUES (?, ?)";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, noPengajuan);
            preparedStatement.setString(2, body);
            preparedStatement.executeUpdate();

            result = true;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error when insertContent: " + e.getMessage());
        }
        finally
        {
            close();
        }

        return result;
    }

    public boolean updateContent(String noPengajuan, String body)
    {
        boolean result = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "UPDATE PDF_PARSER.CONTENT SET BODY_CONTENT = ? WHERE NO_PENGAJUAN = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, body);
            preparedStatement.setString(2, noPengajuan);
            preparedStatement.executeUpdate();

            result = true;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error when updateContent: " + e.getMessage());
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
        {
            //Do nothing
        }
    }
}