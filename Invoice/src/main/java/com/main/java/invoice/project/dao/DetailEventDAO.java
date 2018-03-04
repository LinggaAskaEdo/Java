package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.DetailEvent;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;

/**
 * Created by dery on 3/4/18.
 */
public class DetailEventDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void add(DetailEvent detailEvent)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "INSERT INTO DETAIL_EVENT(PO_EVENT_NO, URAIAN, DETAIL, VOL_1, JENIS_1, VOL_2, JENIS_2, HARGA_SATUAN, TOTAL)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, detailEvent.getPoEventNo());
            preparedStatement.setString(2, detailEvent.getUraian());
            preparedStatement.setString(3, detailEvent.getDetail());
            preparedStatement.setInt(4, detailEvent.getVol1());
            preparedStatement.setString(5, detailEvent.getJenis1());
            preparedStatement.setInt(6, detailEvent.getVol2());
            preparedStatement.setString(7, detailEvent.getJenis2());
            preparedStatement.setBigDecimal(8, detailEvent.getHargaSatuan());
            preparedStatement.setBigDecimal(9, detailEvent.getTotal());

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