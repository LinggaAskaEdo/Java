package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.DetailReimburse;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;

/**
 * Created by dery on 3/4/18.
 */
public class DetailReimbursementDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void add(DetailReimburse detailReimburse)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "INSERT INTO DETAIL_REIMBURSE(PO_EVENT_NO, URAIAN, DETAIL, HARGA)" +
                    "VALUES(?, ?, ?, ?)";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, detailReimburse.getPoEventNo());
            preparedStatement.setString(2, detailReimburse.getUraian());
            preparedStatement.setString(3, detailReimburse.getDetail());
            preparedStatement.setBigDecimal(4, detailReimburse.getHarga());

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