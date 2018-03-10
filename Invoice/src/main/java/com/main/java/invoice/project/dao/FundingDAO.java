package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.Funding;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by dery on 2/25/18.
 */
public class FundingDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void addFunding(Funding funding)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate = dateFormat.format(funding.getTanggal());

            final String query = "INSERT INTO FUNDING(KONTAK_NAME, KONTRAK_ID, TANGGAL, NILAI, KETERANGAN, PPN_IMAGE)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            File file = new File(funding.getImage());
            FileInputStream inputStream = new FileInputStream(file);

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, funding.getKontakName());
            preparedStatement.setString(2, funding.getReff());
            preparedStatement.setString(3, currentDate);
            preparedStatement.setBigDecimal(4, funding.getNilai());
            preparedStatement.setString(5, funding.getKeterangan());
            preparedStatement.setBinaryStream(6, inputStream, file.length());

            preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
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
