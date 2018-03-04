package com.main.java.invoice.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.main.java.invoice.project.pojo.MasterDana;
import com.main.java.invoice.project.preference.StaticPreference;

/**
 * Created by dery on 2/11/18.
 */
public class MasterDanaDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void addUpdate (MasterDana masterDana, int flag)
    {
        String query = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            if (flag == 0)
            {
                query = "INSERT INTO MASTER_DANA(NAME_BANK_ACCOUNT, NO_BANK_ACCOUNT, NAME_ACCOUNT, TOTAL_CASH)" +
                        "VALUES (?, ?, ?, ?)";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterDana.getNameBankAccount());
                preparedStatement.setString(2, masterDana.getNoBankAccount());
                preparedStatement.setString(3, masterDana.getNameAccount());
                preparedStatement.setBigDecimal(4, masterDana.getTotalCash());
            }
            else
            {
                query = "UPDATE MASTER_DANA set NAME_BANK_ACCOUNT = ?, NO_BANK_ACCOUNT = ?, NAME_ACCOUNT = ?, TOTAL_CASH = ? " +
                        "WHERE MASTER_DANA_ID = ?";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterDana.getNameBankAccount());
                preparedStatement.setString(2, masterDana.getNoBankAccount());
                preparedStatement.setString(3, masterDana.getNameAccount());
                preparedStatement.setBigDecimal(4, masterDana.getTotalCash());
                preparedStatement.setInt(5, masterDana.getMasterDanaId());
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

    public List<MasterDana> GetAllMasterDana ()
    {
        List<MasterDana> allMasterDana = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MASTER_DANA_ID, NAME_BANK_ACCOUNT, NO_BANK_ACCOUNT, NAME_ACCOUNT, TOTAL_CASH FROM MASTER_DANA";

            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                MasterDana masterDana = new MasterDana();
                masterDana.setMasterDanaId(resultSet.getInt(1));
                masterDana.setNameBankAccount(resultSet.getString(2));
                masterDana.setNoBankAccount(resultSet.getString(3));
                masterDana.setNameAccount(resultSet.getString(4));
                masterDana.setTotalCash(resultSet.getBigDecimal(5));

                allMasterDana.add(masterDana);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return allMasterDana;
    }

    public MasterDana GetMasterDanaById (MasterDana masterDana)
    {
        MasterDana getMasterDana = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT NAME_BANK_ACCOUNT, NO_BANK_ACCOUNT, NAME_ACCOUNT, TOTAL_CASH FROM MASTER_DANA WHERE MASTER_DANA_ID = ?";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, masterDana.getMasterDanaId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                getMasterDana.setNameBankAccount(resultSet.getString(1));
                getMasterDana.setNoBankAccount(resultSet.getString(2));
                getMasterDana.setNameAccount(resultSet.getString(3));
                getMasterDana.setTotalCash(resultSet.getBigDecimal(4));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return getMasterDana;
    }

    public void DeleteMasterDanaById (MasterDana masterDana)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "DELETE FROM MASTER_DANA WHERE MASTER_DANA_ID = ?";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, masterDana.getMasterDanaId());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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

            if (preparedStatement != null)
            {
                preparedStatement.close();
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
