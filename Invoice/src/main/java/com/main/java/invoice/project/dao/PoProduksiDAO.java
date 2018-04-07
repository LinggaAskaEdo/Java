package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.PoProduksi;
import com.main.java.invoice.project.preference.StaticPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by dery on 3/4/18.
 */
public class PoProduksiDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void add (PoProduksi poProduksi)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String currentDate = dateFormat.format(poProduksi.getTanggal());

            String query = "INSERT INTO PO_PRODUKSI(PO_PRODUKSI_NO, KONTRAK_ID, PRODUKSI, TANGGAL, NILAI_PRODUKSI, KETERANGAN, PPN_IMAGE)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

            File file = new File(poProduksi.getImage());
            FileInputStream inputStream = new FileInputStream(file);

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, poProduksi.getPoProduksiNo());
            preparedStatement.setInt(2, poProduksi.getKontrakId());
            preparedStatement.setString(3, poProduksi.getProduksi());
            preparedStatement.setString(4, currentDate);
            preparedStatement.setBigDecimal(5, poProduksi.getNilaiProduksi());
            preparedStatement.setString(6, poProduksi.getKeterangan());
            preparedStatement.setBinaryStream(7, inputStream, file.length());

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