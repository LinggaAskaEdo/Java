package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.MasterPerusahaan;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dery on 2/24/18.
 */
public class MasterLegalitasDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void addUpdate(MasterPerusahaan masterPerusahaan, int flag)
    {
        String query;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            if (flag == 0)
            {
                query = "INSERT INTO MASTER_PERUSAHAAN(CODE, NAME, PPN_IMAGE, ADDRESS, NO_NPWP, CONTACT_NUMBER, NO_BANK_ACCOUNT, FEE_AGENCY)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterPerusahaan.getCode());
                preparedStatement.setString(2, masterPerusahaan.getName());

                if (masterPerusahaan.getImage() != null)
                {
                    File file = new File(masterPerusahaan.getImage());
                    FileInputStream inputStream = new FileInputStream(file);

                    preparedStatement.setBinaryStream(3, inputStream, (int) file.length());
                }
                else
                {
                    preparedStatement.setBinaryStream(3, null);
                }

                preparedStatement.setString(4, masterPerusahaan.getAddress());
                preparedStatement.setString(5, masterPerusahaan.getNoNpwp());
                preparedStatement.setString(6, masterPerusahaan.getContactNumber());
                preparedStatement.setString(7, masterPerusahaan.getNoBankAccount());
                preparedStatement.setString(8, masterPerusahaan.getFeeAgency());
            }
            else
            {
                query = "UPDATE MASTER_PERUSAHAAN set NAME = ?, PPN_IMAGE = ?, ADDRESS = ?, NO_NPWP = ?, CONTACT_NUMBER = ?, NO_BANK_ACCOUNT = ?, FEE_AGENCY = ?" +
                        "WHERE CODE = ?";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterPerusahaan.getName());

                if (masterPerusahaan.getImage() != null)
                {
                    File file = new File(masterPerusahaan.getImage());
                    FileInputStream inputStream = new FileInputStream(file);
                    preparedStatement.setBinaryStream(2, inputStream, file.length());
                }
                else
                {
                    preparedStatement.setBinaryStream(2, null);
                }

                preparedStatement.setString(3, masterPerusahaan.getAddress());
                preparedStatement.setString(4, masterPerusahaan.getNoNpwp());
                preparedStatement.setString(5, masterPerusahaan.getContactNumber());
                preparedStatement.setString(6, masterPerusahaan.getNoBankAccount());
                preparedStatement.setString(7, masterPerusahaan.getFeeAgency());
                preparedStatement.setString(8, masterPerusahaan.getCode());
            }

            preparedStatement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close();
        }
    }

    public List<MasterPerusahaan> GetAllMasterPerusahaan()
    {
        List<MasterPerusahaan> allMasterMedia = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MASTER_PERUSAHAAN_ID, CODE, NAME, ADDRESS, NO_NPWP, CONTACT_NUMBER, NO_BANK_ACCOUNT, FEE_AGENCY FROM MASTER_PERUSAHAAN";

            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                MasterPerusahaan masterPerusahaan = new MasterPerusahaan();
                masterPerusahaan.setMasterPerusahaanId(resultSet.getInt(1));
                masterPerusahaan.setCode(resultSet.getString(2));
                masterPerusahaan.setName(resultSet.getString(3));
                masterPerusahaan.setAddress(resultSet.getString(4));
                masterPerusahaan.setNoNpwp(resultSet.getString(5));
                masterPerusahaan.setContactNumber(resultSet.getString(6));
                masterPerusahaan.setNoBankAccount(resultSet.getString(7));
                masterPerusahaan.setFeeAgency(resultSet.getString(8));

                allMasterMedia.add(masterPerusahaan);
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

        return allMasterMedia;
    }

    public List<MasterPerusahaan> GetAllMasterPerusahaanComboBox ()
    {
        List<MasterPerusahaan> allMasterMedia = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MASTER_PERUSAHAAN_ID, CODE, NAME, ADDRESS, NO_NPWP, CONTACT_NUMBER, NO_BANK_ACCOUNT, FEE_AGENCY FROM MASTER_PERUSAHAAN";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                MasterPerusahaan masterPerusahaan = new MasterPerusahaan();
                masterPerusahaan.setMasterPerusahaanId(resultSet.getInt(1));
                masterPerusahaan.setCode(resultSet.getString(2));
                masterPerusahaan.setName(resultSet.getString(3));
                masterPerusahaan.setAddress(resultSet.getString(4));
                masterPerusahaan.setNoNpwp(resultSet.getString(5));
                masterPerusahaan.setContactNumber(resultSet.getString(6));
                masterPerusahaan.setNoBankAccount(resultSet.getString(7));
                masterPerusahaan.setFeeAgency(resultSet.getString(8));

                allMasterMedia.add(masterPerusahaan);
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

        return allMasterMedia;
    }

    public MasterPerusahaan GetMasterPerusahaanById(MasterPerusahaan masterPerusahaan)
    {
        MasterPerusahaan getMasterPerusahaan = new MasterPerusahaan();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT NAME, ADDRESS, NO_NPWP, CONTACT_NUMBER, NO_BANK_ACCOUNT, FEE_AGENCY FROM MASTER_PERUSAHAAN WHERE CODE  = ?" ;

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, masterPerusahaan.getCode());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                getMasterPerusahaan.setName(resultSet.getString(1));
                getMasterPerusahaan.setAddress(resultSet.getString(2));
                getMasterPerusahaan.setNoNpwp(resultSet.getString(3));
                getMasterPerusahaan.setContactNumber(resultSet.getString(4));
                getMasterPerusahaan.setNoBankAccount(resultSet.getString(5));
                getMasterPerusahaan.setFeeAgency(resultSet.getString(6));
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

        return getMasterPerusahaan;
    }

    public MasterPerusahaan GetMasterPerusahaanById(int id)
    {
        MasterPerusahaan getMasterPerusahaan = new MasterPerusahaan();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT CODE, NAME, ADDRESS, NO_NPWP, CONTACT_NUMBER, NO_BANK_ACCOUNT, FEE_AGENCY FROM MASTER_PERUSAHAAN WHERE MASTER_PERUSAHAAN_ID = ?" ;

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                getMasterPerusahaan.setCode(resultSet.getString(1));
                getMasterPerusahaan.setName(resultSet.getString(2));
                getMasterPerusahaan.setAddress(resultSet.getString(3));
                getMasterPerusahaan.setNoNpwp(resultSet.getString(4));
                getMasterPerusahaan.setContactNumber(resultSet.getString(5));
                getMasterPerusahaan.setNoBankAccount(resultSet.getString(6));
                getMasterPerusahaan.setFeeAgency(resultSet.getString(7));
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

        return getMasterPerusahaan;
    }

    public void DeleteMasterPerusahaanById (MasterPerusahaan masterPerusahaan)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "DELETE FROM MASTER_PERUSAHAAN WHERE CODE = ?";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, masterPerusahaan.getCode());

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