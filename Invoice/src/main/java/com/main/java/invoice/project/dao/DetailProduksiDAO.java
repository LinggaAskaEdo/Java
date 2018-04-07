package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.DetailProduksi;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;

/**
 * Created by dery on 3/4/18.
 */
public class DetailProduksiDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void add(DetailProduksi detailProduksi)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "INSERT INTO DETAIL_PRODUKSI(PO_PRODUKSI_ID, MEDIA, DURASI, HARI, LOKASI, PRE_PRODUKSI_URAIAN," +
                    "PRE_PRODUKSI_JENIS, PRODUKSI_JENIS, PRODUKSI_JUMLAH, PRODUKSI_BARANG, PRODUKSI_HARGA_SATUAN," +
                    "PRODUKSI_TOTAL_HARGA, POST_PRODUKSI_BARANG, POST_PRODUKSI_TOTAL_HARGA)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, detailProduksi.getPoProduksiNo());
            preparedStatement.setString(2, detailProduksi.getMedia());
            preparedStatement.setString(3, detailProduksi.getDurasi());
            preparedStatement.setString(4, detailProduksi.getHari());
            preparedStatement.setString(5, detailProduksi.getLokasi());
            preparedStatement.setString(6, detailProduksi.getPreProduksiUraian());
            preparedStatement.setString(7, detailProduksi.getPreProduksiJenis());
            preparedStatement.setString(8, detailProduksi.getProduksiJenis());
            preparedStatement.setString(9, detailProduksi.getProduksiJumlah());
            preparedStatement.setString(10, detailProduksi.getProduksiBarang());
            preparedStatement.setBigDecimal(11, detailProduksi.getProduksiHargaSatuan());
            preparedStatement.setBigDecimal(12, detailProduksi.getProduksiTotalHarga());
            preparedStatement.setString(13, detailProduksi.getPostProduksiBarang());
            preparedStatement.setBigDecimal(14, detailProduksi.getPostProduksiTotalHarga());

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