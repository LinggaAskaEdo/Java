package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.TagihanReimburse;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by dery on 3/4/18.
 */
public class TagihanReimbursementDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void add(TagihanReimburse tagihanReimburse)
    {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate = dateFormat.format(tagihanReimburse.getTanggal());

            String query = "INSERT INTO TAGIHAN_REIMBURSE(PO_EVENT_NO, PO_NOMOR, CATATAN, TANGGAL, MASTER_MEDIA_ID, KETERANGAN, PPN_IMAGE)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

            File file = new File(tagihanReimburse.getImage());
            FileInputStream inputStream = new FileInputStream(file);

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, tagihanReimburse.getPoEventNo());
            preparedStatement.setString(2, tagihanReimburse.getPoNomor());
            preparedStatement.setString(3, tagihanReimburse.getCatatan());
            preparedStatement.setString(4, currentDate);
            preparedStatement.setInt(5, tagihanReimburse.getMasterDanaId());
            preparedStatement.setString(6, tagihanReimburse.getKeterangan());
            preparedStatement.setBinaryStream(7, inputStream,file.length());

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