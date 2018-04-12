package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.PoMedia;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by dery on 3/4/18.
 */
public class PoMediaDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void add(PoMedia poMedia)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate = dateFormat.format(poMedia.getTanggalTayang());

            String query = "INSERT INTO PO_MEDIA(PO_MEDIA_NO, KONTRAK_ID, PEKERJAAN_KEMENTERIAN, MASTER_MEDIA_ID, TANGGAL_TAYANG" +
                    "UKURAN, HARGA, PPN, KETERANGAN, PPN_IMAGE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, poMedia.getPoMediaNo());
            preparedStatement.setInt(2, poMedia.getKontrakId());
            preparedStatement.setString(3, poMedia.getPekerjaanKementerian());
            preparedStatement.setInt(4, poMedia.getMasterMediaId());
            preparedStatement.setString(5, currentDate);
            preparedStatement.setString(6, poMedia.getUkuran());
            preparedStatement.setBigDecimal(7, poMedia.getHarga());
            preparedStatement.setBigDecimal(8, poMedia.getPpn());
            preparedStatement.setString(9, poMedia.getKeterangan());

            if (poMedia.getImage() != null)
            {
                File file = new File(poMedia.getImage());
                FileInputStream inputStream = new FileInputStream(file);

                preparedStatement.setBinaryStream(10, inputStream, file.length());
            }
            else
            {
                preparedStatement.setBinaryStream(10, null);
            }
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