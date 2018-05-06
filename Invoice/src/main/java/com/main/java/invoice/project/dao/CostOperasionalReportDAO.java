package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.CostOperasionalReport;
import com.main.java.invoice.project.pojo.FundingReport;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dery on 5/6/18.
 */
public class CostOperasionalReportDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<CostOperasionalReport> GetAllCostOperasionalReportHarian (Date date)
    {
        List<CostOperasionalReport> allCostOperasionalReports = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate1 = dateFormat.format(date);

            String query = "SELECT CO.COST_OPERASIONAL_ID, " +
                    "CO.MASTER_DANA_ID, " +
                    "CO.PIC, " +
                    "CO.KEPERLUAN, " +
                    "CO.TANGGAL_PEMBELIAN, " +
                    "TO_BASE64(CO.PPN_IMAGE), " +
                    "MD.NAME_BANK_ACCOUNT, " +
                    "MD.NO_BANK_ACCOUNT " +
                    "FROM " +
                    "    COST_OPERASIONAL AS CO " +
                    "JOIN " +
                    "    MASTER_DANA AS MD ON CO.MASTER_DANA_ID = MD.MASTER_DANA_ID " +
                    "WHERE " +
                    "    CO.TANGGAL_PEMBELIAN = '" + currentDate1 + "'";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                CostOperasionalReport costOperasionalReport = new CostOperasionalReport();
                costOperasionalReport.setCostOperasionalId(resultSet.getString(1));
                costOperasionalReport.setMasterDanaId(resultSet.getString(2));
                costOperasionalReport.setKeperluan(resultSet.getString(3));
                costOperasionalReport.setTanggalPembelian(resultSet.getString(4));
                costOperasionalReport.setImageCostOperasional(resultSet.getString(5));
                costOperasionalReport.setNamaBankAkun(resultSet.getString(6));
                costOperasionalReport.setNoBankAkun(resultSet.getString(7));

                allCostOperasionalReports.add(costOperasionalReport);
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

        return allCostOperasionalReports;
    }

    public List<CostOperasionalReport> GetAllCostOperasionalReportBulanan (Date date1, Date date2)
    {
        List<CostOperasionalReport> allCostOperasionalReports = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate1 = dateFormat.format(date1);
            String currentDate2 = dateFormat.format(date2);

            String query = "SELECT CO.COST_OPERASIONAL_ID, " +
                    "CO.MASTER_DANA_ID, " +
                    "CO.PIC, " +
                    "CO.KEPERLUAN, " +
                    "CO.TANGGAL_PEMBELIAN, " +
                    "TO_BASE64(CO.PPN_IMAGE), " +
                    "MD.NAME_BANK_ACCOUNT, " +
                    "MD.NO_BANK_ACCOUNT " +
                    "FROM " +
                    "    COST_OPERASIONAL AS CO " +
                    "JOIN " +
                    "    MASTER_DANA AS MD ON CO.MASTER_DANA_ID = MD.MASTER_DANA_ID " +
                    "WHERE " +
                    "    CO.TANGGAL_PEMBELIAN BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                CostOperasionalReport costOperasionalReport = new CostOperasionalReport();
                costOperasionalReport.setCostOperasionalId(resultSet.getString(1));
                costOperasionalReport.setMasterDanaId(resultSet.getString(2));
                costOperasionalReport.setKeperluan(resultSet.getString(3));
                costOperasionalReport.setTanggalPembelian(resultSet.getString(4));
                costOperasionalReport.setImageCostOperasional(resultSet.getString(5));
                costOperasionalReport.setNamaBankAkun(resultSet.getString(6));
                costOperasionalReport.setNoBankAkun(resultSet.getString(7));

                allCostOperasionalReports.add(costOperasionalReport);
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

        return allCostOperasionalReports;
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
