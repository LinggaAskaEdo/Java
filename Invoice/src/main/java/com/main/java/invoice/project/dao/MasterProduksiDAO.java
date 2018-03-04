package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.MasterProduksi;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dery on 2/24/18.
 */
public class MasterProduksiDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void addUpdate(MasterProduksi masterProduksi, int flag)
    {
        String query = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            if (flag == 0)
            {
                query = "INSERT INTO MASTER_PRODUKSI(AGENT_PRODUKSI, NAME, ADDRESS, NO_NPWP, INFORMATION)" +
                        "VALUES(?, ?, ?, ?, ?)";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterProduksi.getAgentProduksi());
                preparedStatement.setString(2, masterProduksi.getName());
                preparedStatement.setString(3, masterProduksi.getAddress());
                preparedStatement.setString(4, masterProduksi.getNoNpwp());
                preparedStatement.setString(5, masterProduksi.getInformation());
            }
            else
            {
                query = "UPDATE MASTER_PRODUKSI set AGENT_PRODUKSI = ?, NAME = ?, ADDRESS = ?, NO_NPWP = ?, INFORMATION = ?" +
                        "WHERE MASTER_PRODUKSI_ID = ?";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterProduksi.getAgentProduksi());
                preparedStatement.setString(2, masterProduksi.getName());
                preparedStatement.setString(3, masterProduksi.getAddress());
                preparedStatement.setString(4, masterProduksi.getNoNpwp());
                preparedStatement.setString(5, masterProduksi.getInformation());
                preparedStatement.setInt(6, masterProduksi.getMasterProduksiId());
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

    public List<MasterProduksi> GetAllMasterProduksi ()
    {
        List<MasterProduksi> allMasterProduksi = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MASTER_PRODUKSI_ID, AGENT_PRODUKSI, NAME, ADDRESS, NO_NPWP, INFORMATION FROM MASTER_PRODUKSI";

            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                MasterProduksi masterProduksi = new MasterProduksi();
                masterProduksi.setMasterProduksiId(resultSet.getInt(1));
                masterProduksi.setAgentProduksi(resultSet.getString(2));
                masterProduksi.setName(resultSet.getString(3));
                masterProduksi.setAddress(resultSet.getString(4));
                masterProduksi.setNoNpwp(resultSet.getString(5));
                masterProduksi.setInformation(resultSet.getString(6));

                allMasterProduksi.add(masterProduksi);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return allMasterProduksi;
    }

    public MasterProduksi GetMasterProduksiById(MasterProduksi masterProduksi)
    {
        MasterProduksi getMasterProduksi = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT AGENT_PRODUKSI, NAME, ADDRESS, NO_NPWP, INFORMATION FROM MASTER_PRODUKSI WHERE MASTER_PRODUKSI_ID = ?" ;

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, masterProduksi.getMasterProduksiId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                getMasterProduksi.setAgentProduksi(resultSet.getString(1));
                getMasterProduksi.setName(resultSet.getString(2));
                getMasterProduksi.setAddress(resultSet.getString(3));
                getMasterProduksi.setNoNpwp(resultSet.getString(4));
                getMasterProduksi.setInformation(resultSet.getString(5));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return getMasterProduksi;
    }

    public void DeleteMasterProduksiById (MasterProduksi masterProduksi)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "DELETE FROM MASTER_PRODUKSI WHERE MASTER_PRODUKSI_ID = ?";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, masterProduksi.getMasterProduksiId());

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
