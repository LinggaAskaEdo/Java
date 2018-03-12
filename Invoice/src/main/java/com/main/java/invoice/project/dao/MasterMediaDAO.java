package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.MasterMedia;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dery on 2/24/18.
 */
public class MasterMediaDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void addUpdate(MasterMedia masterMedia, int flag)
    {
        String query = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            if (flag == 0)
            {
                query = "INSERT INTO MASTER_MEDIA(COMPANY_NAME, MEDIA_NAME, ADDRESS, NO_NPWP, BILL_COMMITMENT, INFORMATION)" +
                        "VALUES(?, ?, ?, ?, ?, ?)";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterMedia.getCompanyName());
                preparedStatement.setString(2, masterMedia.getMediaName());
                preparedStatement.setString(3, masterMedia.getAddress());
                preparedStatement.setString(4, masterMedia.getNoNpwp());
                preparedStatement.setString(5, masterMedia.getBillCommitment());
                preparedStatement.setString(6, masterMedia.getInformation());
            }
            else
            {

                query = "UPDATE MASTER_MEDIA set MEDIA_NAME = ?, ADDRESS = ?, NO_NPWP = ?, BILL_COMMITMENT = ?, INFORMATION = ?" +
                        "WHERE COMPANY_NAME = ?";
                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterMedia.getMediaName());
                preparedStatement.setString(2, masterMedia.getAddress());
                preparedStatement.setString(3, masterMedia.getNoNpwp());
                preparedStatement.setString(4, masterMedia.getBillCommitment());
                preparedStatement.setString(5, masterMedia.getInformation());
                preparedStatement.setString(6, masterMedia.getCompanyName());
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
    
    public List<MasterMedia> GetAllMasterMedia()
    {
        List<MasterMedia> allMasterMedia = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MASTER_MEDIA_ID, COMPANY_NAME, MEDIA_NAME, ADDRESS, NO_NPWP, BILL_COMMITMENT, INFORMATION FROM MASTER_MEDIA";

            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                MasterMedia masterMedia = new MasterMedia();
                masterMedia.setMasterMediaId(resultSet.getInt(1));
                masterMedia.setCompanyName(resultSet.getString(2));
                masterMedia.setMediaName(resultSet.getString(3));
                masterMedia.setAddress(resultSet.getString(4));
                masterMedia.setNoNpwp(resultSet.getString(5));
                masterMedia.setBillCommitment(resultSet.getString(6));
                masterMedia.setInformation(resultSet.getString(7));

                allMasterMedia.add(masterMedia);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return allMasterMedia;
    }

    public List<MasterMedia> GetAllMasterMediaComboBox()
    {
        List<MasterMedia> allMasterMedia = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MASTER_MEDIA_ID, COMPANY_NAME, MEDIA_NAME, ADDRESS, NO_NPWP, BILL_COMMITMENT, INFORMATION FROM MASTER_MEDIA";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                MasterMedia masterMedia = new MasterMedia();
                masterMedia.setMasterMediaId(resultSet.getInt(1));
                masterMedia.setCompanyName(resultSet.getString(2));
                masterMedia.setMediaName(resultSet.getString(3));
                masterMedia.setAddress(resultSet.getString(4));
                masterMedia.setNoNpwp(resultSet.getString(5));
                masterMedia.setBillCommitment(resultSet.getString(6));
                masterMedia.setInformation(resultSet.getString(7));

                allMasterMedia.add(masterMedia);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return allMasterMedia;
    }
    
    public MasterMedia GetMasterMediaById(MasterMedia masterMedia)
    {
        MasterMedia getMasterMedia = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MASTER_MEDIA_ID, MEDIA_NAME, ADDRESS, NO_NPWP, BILL_COMMITMENT, INFORMATION FROM MASTER_MEDIA WHERE COMPANY_NAME = ? LIMIT 1" ;

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, masterMedia.getCompanyName());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                getMasterMedia.setMasterMediaId(resultSet.getInt(1));
                getMasterMedia.setMediaName(resultSet.getString(2));
                getMasterMedia.setAddress(resultSet.getString(3));
                getMasterMedia.setNoNpwp(resultSet.getString(4));
                getMasterMedia.setBillCommitment(resultSet.getString(5));
                getMasterMedia.setInformation(resultSet.getString(6));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return getMasterMedia;
    }

    public void DeleteMasterMediaById (MasterMedia masterMedia)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "DELETE FROM MASTER_MEDIA WHERE COMPANY_NAME = ?";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, masterMedia.getCompanyName());

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
