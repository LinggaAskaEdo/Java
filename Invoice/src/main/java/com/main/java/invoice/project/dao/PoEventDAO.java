package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.PoEvent;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by dery on 3/4/18.
 */
public class PoEventDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void add (PoEvent poEvent)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate = dateFormat.format(poEvent.getTanggal());

            String query = "INSERT INTO PO_EVENT(PO_EVENT_NO, KONTRAK_ID, KEGIATAN, TANGGAL, JUMLAH, KETERANGAN, BUKTI)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

            File file = new File(poEvent.getImage());
            FileInputStream inputStream = new FileInputStream(file);

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, poEvent.getPoEventNo());
            preparedStatement.setString(2, poEvent.getKontrakId());
            preparedStatement.setString(3, poEvent.getKegiatan());
            preparedStatement.setString(4, currentDate);
            preparedStatement.setBigDecimal(5, poEvent.getJumlah());
            preparedStatement.setString(6, poEvent.getKeterangan());
            preparedStatement.setBinaryStream(7,inputStream,file.length());

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