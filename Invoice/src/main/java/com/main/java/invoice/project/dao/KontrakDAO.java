package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.Kontrak;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KontrakDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

    public void addUpdate(Kontrak kontrak, int flag)
    {
        String query;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate = dateFormat.format(kontrak.getDate());

            if (flag == 0)
            {
                query = "INSERT INTO KONTRAK(NO_KONTRAK, MASTER_PERUSAHAAN_ID, PROJECT, DATE, NILAI_KONTRAK, DPP, PPN, PPH_23, SP_2D, PAID)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, kontrak.getNoKontrak());
                preparedStatement.setInt(2, kontrak.getMasterPerusahaanId());
                preparedStatement.setString(3, kontrak.getProject());
                preparedStatement.setString(4, currentDate);
                preparedStatement.setBigDecimal(5, kontrak.getNilaiKontrak());
                preparedStatement.setBigDecimal(6, kontrak.getDpp());
                preparedStatement.setBigDecimal(7, kontrak.getPpn());
                preparedStatement.setBigDecimal(8, kontrak.getPph23());
                preparedStatement.setBigDecimal(9, kontrak.getSp2d());
                preparedStatement.setInt(10, kontrak.getPaid());
            }
            else
            {
                query = "UPDATE MASTER_DANA set MASTER_PERUSAHAAN_ID = ?, PROJECT = ?, DATE = ?, NILAI_KONTRAK = ?, DPP = ?, PPN = ?, PPH_23 = ?, SP_2D = ?, PAID = ?" +
                        "WHERE NO_KONTRAK = ?";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setInt(1, kontrak.getMasterPerusahaanId());
                preparedStatement.setString(2, kontrak.getProject());
                preparedStatement.setString(3, currentDate);
                preparedStatement.setBigDecimal(4, kontrak.getNilaiKontrak());
                preparedStatement.setBigDecimal(5, kontrak.getDpp());
                preparedStatement.setBigDecimal(6, kontrak.getPpn());
                preparedStatement.setBigDecimal(7, kontrak.getPph23());
                preparedStatement.setBigDecimal(8, kontrak.getSp2d());
                preparedStatement.setInt(9, kontrak.getPaid());
                preparedStatement.setString(10, kontrak.getNoKontrak());
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

    public List<Kontrak> GetAllKontrakComboBox ()
    {
        List<Kontrak> allKontrak = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT KONTRAK_ID, NO_KONTRAK, MASTER_PERUSAHAAN_ID, PROJECT, DATE, NILAI_KONTRAK, DPP, PPN, PPH_23, SP_2D, PAID FROM KONTRAK";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                Kontrak kontrak = new Kontrak();
                kontrak.setKontrakId(resultSet.getInt(1));
                kontrak.setNoKontrak(resultSet.getString(2));
                kontrak.setMasterPerusahaanId(resultSet.getInt(3));
                kontrak.setProject(resultSet.getString(4));
                kontrak.setDate(resultSet.getDate(5));
                kontrak.setNilaiKontrak(resultSet.getBigDecimal(6));
                kontrak.setDpp(resultSet.getBigDecimal(7));
                kontrak.setPpn(resultSet.getBigDecimal(8));
                kontrak.setPph23(resultSet.getBigDecimal(9));
                kontrak.setSp2d(resultSet.getBigDecimal(10));
                kontrak.setPaid(resultSet.getInt(11));

                allKontrak.add(kontrak);
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

        return allKontrak;
    }

    public Kontrak GetKontrakById(Kontrak kontrak)
    {
        Kontrak getKontrak = new Kontrak();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT KONTRAK_ID, MASTER_PERUSAHAAN_ID, PROJECT, DATE, NILAI_KONTRAK, DPP, PPN, PPH_23, SP_2D, PAID FROM KONTRAK WHERE NO_KONTRAK  = ?" ;

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, kontrak.getNoKontrak());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                getKontrak.setKontrakId(resultSet.getInt(1));
                getKontrak.setMasterPerusahaanId(resultSet.getInt(2));
                getKontrak.setProject(resultSet.getString(3));
                getKontrak.setDate(resultSet.getDate(4));
                getKontrak.setNilaiKontrak(resultSet.getBigDecimal(5));
                getKontrak.setDpp(resultSet.getBigDecimal(6));
                getKontrak.setPpn(resultSet.getBigDecimal(7));
                getKontrak.setPph23(resultSet.getBigDecimal(8));
                getKontrak.setSp2d(resultSet.getBigDecimal(9));
                getKontrak.setPaid(resultSet.getInt(10));
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

        return getKontrak;
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