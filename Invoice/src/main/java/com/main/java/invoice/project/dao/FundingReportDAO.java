package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.FundingReport;
import com.main.java.invoice.project.pojo.MasterClient;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by dery on 5/5/18.
 */
public class FundingReportDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<FundingReport> GetAllFundingReportHarian (String klien, Date date1)
    {
        List<FundingReport> allFundingReport = new ArrayList<>();
        String parameter;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate1 = dateFormat.format(date1);

            if (! klien.equals(""))
            {
                parameter = "(CASE WHEN f.CHECK_REFF = '1' THEN k.PROJECT = '" +klien+ "' ELSE md.NAME_ACCOUNT = md.NAME_ACCOUNT " +
                        "END) AND f.TANGGAL = " + currentDate1;
            }
            else
            {
                parameter = "f.TANGGAL = '" + currentDate1 + "'";
            }

            String query = "SELECT " +
                    "f.FUNDING_ID, " +
                    "f.KONTAK_NAME, " +
                    "(CASE " +
                    "WHEN f.CHECK_REFF = '1' THEN k.NO_KONTRAK " +
                    "ELSE md.NAME_ACCOUNT " +
                    "END) AS REFF, " +
                    "f.TANGGAL, " +
                    "f.NILAI, " +
                    "f.KETERANGAN, " +
                    "f.PPN_IMAGE" +
                    "FROM " +
                    "FUNDING AS f " +
                    "LEFT JOIN " +
                    "KONTRAK AS k ON (f.KONTRAK_ID = k.KONTRAK_ID) " +
                    "AND (f.CHECK_REFF = 1) " +
                    "LEFT JOIN " +
                    "MASTER_DANA AS md ON (f.KONTRAK_ID = md.MASTER_DANA_ID) " +
                    "AND (f.CHECK_REFF = 0) " +
                    "WHERE " + parameter + ";";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                FundingReport fundingReport = new FundingReport();
                fundingReport.setFundingId(resultSet.getString(1));
                fundingReport.setKontrakName(resultSet.getString(2));
                fundingReport.setReff(resultSet.getString(3));
                fundingReport.setTanggal(resultSet.getString(4));
                fundingReport.setNilai(resultSet.getString(5));
                fundingReport.setKeterangan(resultSet.getString(6));

                if (resultSet.getBlob(7) != null)
                {
                    fundingReport.setImageFunding(resultSet.getBlob(7));
                }

                allFundingReport.add(fundingReport);
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

        return allFundingReport;
    }

    public List<FundingReport> GetAllFundingReportBulanan (String klien, Date date1, Date date2)
    {
        List<FundingReport> allFundingReport = new ArrayList<>();
        String parameter;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate1 = dateFormat.format(date1);
            String currentDate2 = dateFormat.format(date2);

            if (! klien.equals(""))
            {
                parameter = "(CASE WHEN f.CHECK_REFF = '1' THEN k.PROJECT = '" +klien+ "' ELSE md.NAME_ACCOUNT = md.NAME_ACCOUNT " +
                        "END) AND f.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else
            {
                parameter = "f.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }

            String query = "SELECT " +
                    "f.FUNDING_ID, " +
                    "f.KONTAK_NAME, " +
                    "(CASE " +
                    "WHEN f.CHECK_REFF = '1' THEN k.NO_KONTRAK " +
                    "ELSE md.NAME_ACCOUNT " +
                    "END) AS REFF, " +
                    "f.TANGGAL, " +
                    "f.NILAI, " +
                    "f.KETERANGAN, " +
                    "f.PPN_IMAGE" +
                    "FROM " +
                    "FUNDING AS f " +
                    "LEFT JOIN " +
                    "KONTRAK AS k ON (f.KONTRAK_ID = k.KONTRAK_ID) " +
                    "AND (f.CHECK_REFF = 1) " +
                    "LEFT JOIN " +
                    "MASTER_DANA AS md ON (f.KONTRAK_ID = md.MASTER_DANA_ID) " +
                    "AND (f.CHECK_REFF = 0) " +
                    "WHERE " + parameter + ";";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                FundingReport fundingReport = new FundingReport();
                fundingReport.setFundingId(resultSet.getString(1));
                fundingReport.setKontrakName(resultSet.getString(2));
                fundingReport.setReff(resultSet.getString(3));
                fundingReport.setTanggal(resultSet.getString(4));
                fundingReport.setNilai(resultSet.getString(5));
                fundingReport.setKeterangan(resultSet.getString(6));

                if (resultSet.getBlob(7) != null)
                {
                    fundingReport.setImageFunding(resultSet.getBlob(7));
                }

                allFundingReport.add(fundingReport);
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

        return allFundingReport;
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
