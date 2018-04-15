package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.TagihanMedia;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by dery on 3/4/18.
 */
public class TagihanMediaDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void add(TagihanMedia tagihanMediaList)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate = dateFormat.format(tagihanMediaList.getTanggal());

            String query = "INSERT INTO TAGIHAN_MEDIA(PO_MEDIA_NO, INVOICE_MEDIA, TANGGAL, NILAI_TAGIHAN, MASTER_MEDIA_ID, PPN_IMAGE)" +
                    "VALUES(?, ?, ?, ?, ?, ?)";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, tagihanMediaList.getPoMediaNo());
            preparedStatement.setString(2, tagihanMediaList.getInvoiceMedia());
            preparedStatement.setString(3, currentDate);
            preparedStatement.setBigDecimal(4, tagihanMediaList.getNilaiTagihan());
            preparedStatement.setInt(5, tagihanMediaList.getMasterDanaId());

            if (tagihanMediaList.getImage() != null)
            {
                File file = new File(tagihanMediaList.getImage());
                FileInputStream inputStream = new FileInputStream(file);

                preparedStatement.setBinaryStream(6, inputStream, file.length());
            }
            else
            {
                preparedStatement.setBinaryStream(6, null);
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