package com.pdf.parser.dao;

import com.pdf.parser.model.Transaction;
import com.pdf.parser.preference.StaticPreference;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParserDAO
{
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public boolean checkUser(String user, String pass)
    {
        boolean result = false;

        try
        {
            Class.forName(StaticPreference.DRIVER);
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT ID FROM PDF_PARSER.USER WHERE USER_NAME = ? and USER_PASSWORD = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
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
            Class.forName(StaticPreference.DRIVER);
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT ID FROM PDF_PARSER.CONTENT WHERE NO_PENGAJUAN = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, noPengajuan);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
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
        try
        {
            Class.forName(StaticPreference.DRIVER);
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "INSERT INTO PDF_PARSER.CONTENT(NO_PENGAJUAN, BODY_CONTENT) VALUES (?, ?)";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, noPengajuan);
            preparedStatement.setString(2, body);

            return preparedStatement.executeUpdate() > 0;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error when insertContent: " + e.getMessage());
        }
        finally
        {
            close();
        }

        return false;
    }

    public boolean updateContent(String noPengajuan, String body)
    {
        try
        {
            Class.forName(StaticPreference.DRIVER);
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "UPDATE PDF_PARSER.CONTENT SET BODY_CONTENT = ? WHERE NO_PENGAJUAN = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, body);
            preparedStatement.setString(2, noPengajuan);

            return preparedStatement.executeUpdate() > 0;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error when updateContent: " + e.getMessage());
        }
        finally
        {
            close();
        }

        return false;
    }

    public List<String> loadNomorPengajuan()
    {
        List<String> result = new ArrayList<>();

        try
        {
            Class.forName(StaticPreference.DRIVER);
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT NO_CONTENT FROM PDF_PARSER.CONTENT";
            preparedStatement = connect.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                result.add(resultSet.getString("NO_CONTENT"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error when loadNomorPengajuan: " + e.getMessage());
        }
        finally
        {
            close();
        }

        return result;
    }

    public Transaction getTransactionByNumber(String number)
    {
        Transaction transaction = new Transaction();

        try
        {
            Class.forName(StaticPreference.DRIVER);
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT NO_CONTENT, BODY_CONTENT FROM PDF_PARSER.CONTENT WHERE NO_CONTENT = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, number);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                transaction.setNoContent(resultSet.getString("NO_CONTENT"));
                transaction.setBodyContent(resultSet.getString("BODY_CONTENT"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error when getTransactionByNumber: " + e.getMessage());
        }
        finally
        {
            close();
        }

        return transaction;
    }

    private void close()
    {
        try
        {
            if (connect != null)
            {
                connect.close();
            }

            if (resultSet != null)
            {
                resultSet.close();
            }
        }
        catch (Exception ignored)
        {
            //Do nothing
        }
    }
}