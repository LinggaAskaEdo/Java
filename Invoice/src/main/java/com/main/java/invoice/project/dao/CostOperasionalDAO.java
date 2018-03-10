package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.CostOperasional;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by dery on 2/25/18.
 */
public class CostOperasionalDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void addCostOperasional (CostOperasional costOperasional) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate = dateFormat.format(costOperasional.getTanggalPemebelian());

            final String query = "INSERT INTO COST_OPERASIONAL(MASTER_DANA_ID, PIC, KEPERLUAN, TANGGAL_PEMBELIAN, PPN_IMAGE)" +
                    "VALUES(?, ?, ?, ?, ?)";

            File file = new File(costOperasional.getImage());
            FileInputStream inputStream = new FileInputStream(file);

        preparedStatement = connect.prepareStatement(query);
        preparedStatement.setString(1, costOperasional.getMasterDana());
        preparedStatement.setString(2, costOperasional.getPic());
        preparedStatement.setString(3, costOperasional.getKeperluan());
        preparedStatement.setString(4, currentDate);
        preparedStatement.setBinaryStream(5, inputStream, file.length());

        preparedStatement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally
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
