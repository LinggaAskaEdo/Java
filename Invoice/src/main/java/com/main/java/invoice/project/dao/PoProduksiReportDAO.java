package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.CostOperasionalReport;
import com.main.java.invoice.project.pojo.PoProduksiReport;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dery on 5/7/18.
 */
public class PoProduksiReportDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<PoProduksiReport> GetAllPoProduksiReportHarian (String perusahaan, String klien, Date date)
    {
        List<PoProduksiReport> allPoProduksiReport = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);
            String parameter = "";
            String currentDate1 = dateFormat.format(date);

            if(!perusahaan.equals("") && klien.equals(""))
            {
                parameter = "mp.NAME = '"+perusahaan+"' AND pp.TANGGAL = '" + currentDate1 + "'";
            }
            else if (perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "k.PROJECT = '"+klien+"' AND pp.TANGGAL = '" + currentDate1 + "'";
            }
            else if (!perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "mp.NAME = '" +perusahaan+ "' AND k.PROJECT = '"+klien+"' AND pp.TANGGAL = '" + currentDate1 + "'";
            }
            else
            {
                parameter = "pp.TANGGAL = '" + currentDate1 + "'";
            }

            String query = "SELECT " +
                    "pp.PO_PRODUKSI_ID, " +
                    "pp.PO_PRODUKSI_NO, " +
                    "k.NO_KONTRAK, " +
                    "pp.PRODUKSI, " +
                    "pp.TANGGAL, " +
                    "pp.NILAI_PRODUKSI, " +
                    "pp.KETERANGAN, " +
                    "pp.PPN_IMAGE, " +
                    "dp.DETAIL_PRODUKSI_ID, " +
                    "dp.MEDIA, " +
                    "dp.DURASI, " +
                    "dp.HARI, " +
                    "dp.LOKASI, " +
                    "dp.PRE_PRODUKSI_URAIAN, " +
                    "dp.PRE_PRODUKSI_JENIS, " +
                    "dp.PRODUKSI_JENIS, " +
                    "dp.PRODUKSI_JUMLAH, " +
                    "dp.PRODUKSI_BARANG, " +
                    "dp.PRODUKSI_HARGA_SATUAN, " +
                    "dp.PRODUKSI_TOTAL_HARGA, " +
                    "dp.POST_PRODUKSI_BARANG, " +
                    "dp.POST_PRODUKSI_TOTAL_HARGA " +
                    "FROM " +
                    "PO_PRODUKSI AS pp " +
                    "    LEFT JOIN " +
                    "KONTRAK AS k ON pp.KONTRAK_ID = k.KONTRAK_ID " +
                    "    LEFT JOIN " +
                    "DETAIL_PRODUKSI AS dp ON pp.PO_PRODUKSI_NO = dp.PO_PRODUKSI_NO " +
                    "    LEFT JOIN " +
                    "MASTER_PERUSAHAAN AS mp ON k.MASTER_PERUSAHAAN_ID = mp.MASTER_PERUSAHAAN_ID " +
                    "WHERE "+parameter+";";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                PoProduksiReport poProduksiReport = new PoProduksiReport();
                poProduksiReport.setPoProduksiId(resultSet.getString(1));
                poProduksiReport.setPoProduksiNo(resultSet.getString(2));
                poProduksiReport.setNoKontrak(resultSet.getString(3));
                poProduksiReport.setProduksi(resultSet.getString(4));
                poProduksiReport.setTanggal(resultSet.getString(5));
                poProduksiReport.setNilaiProduksi(resultSet.getString(6));
                poProduksiReport.setKeterangan(resultSet.getString(7));

                if (resultSet.getBlob(8) != null)
                {
                    poProduksiReport.setImagePoProduksi(resultSet.getBlob(8));
                }

                poProduksiReport.setDetailProduksiId(resultSet.getString(9));
                poProduksiReport.setMedia(resultSet.getString(10));
                poProduksiReport.setDurasi(resultSet.getString(11));
                poProduksiReport.setHari(resultSet.getString(12));
                poProduksiReport.setLokasi(resultSet.getString(13));
                poProduksiReport.setPreProduksiUraian(resultSet.getString(14));
                poProduksiReport.setPreProduksiJenis(resultSet.getString(15));
                poProduksiReport.setProduksiJenis(resultSet.getString(16));
                poProduksiReport.setProduksiJumlah(resultSet.getString(17));
                poProduksiReport.setProduksiBarang(resultSet.getString(18));
                poProduksiReport.setProduksiHargaSatuan(resultSet.getString(19));
                poProduksiReport.setProduksiTotalHarga(resultSet.getString(20));
                poProduksiReport.setPostProduksiBarang(resultSet.getString(21));
                poProduksiReport.setPostProduksiTotalHarga(resultSet.getString(22));
                allPoProduksiReport.add(poProduksiReport);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close();
        }

        return allPoProduksiReport;
    }

    public List<PoProduksiReport> GetAllPoProduksiReportBulanan (String perusahaan, String klien, Date date1, Date date2)
    {
        List<PoProduksiReport> allPoProduksiReport = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);
            String parameter = "";
            String currentDate1 = dateFormat.format(date1);
            String currentDate2 = dateFormat.format(date2);

            if(!perusahaan.equals("") && klien.equals(""))
            {
                parameter = "mp.NAME = '"+perusahaan+"' AND pp.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else if (perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "k.PROJECT = '"+klien+"' AND pp.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else if (!perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "mp.NAME = '" +perusahaan+ "' AND k.PROJECT = '"+klien+"' AND pp.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else
            {
                parameter = "pp.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }

            String query = "SELECT " +
                    "pp.PO_PRODUKSI_ID, " +
                    "pp.PO_PRODUKSI_NO, " +
                    "k.NO_KONTRAK, " +
                    "pp.PRODUKSI, " +
                    "pp.TANGGAL, " +
                    "pp.NILAI_PRODUKSI, " +
                    "pp.KETERANGAN, " +
                    "pp.PPN_IMAGE, " +
                    "dp.DETAIL_PRODUKSI_ID, " +
                    "dp.MEDIA, " +
                    "dp.DURASI, " +
                    "dp.HARI, " +
                    "dp.LOKASI, " +
                    "dp.PRE_PRODUKSI_URAIAN, " +
                    "dp.PRE_PRODUKSI_JENIS, " +
                    "dp.PRODUKSI_JENIS, " +
                    "dp.PRODUKSI_JUMLAH, " +
                    "dp.PRODUKSI_BARANG, " +
                    "dp.PRODUKSI_HARGA_SATUAN, " +
                    "dp.PRODUKSI_TOTAL_HARGA, " +
                    "dp.POST_PRODUKSI_BARANG, " +
                    "dp.POST_PRODUKSI_TOTAL_HARGA " +
                    "FROM " +
                    "PO_PRODUKSI AS pp " +
                    "    LEFT JOIN " +
                    "KONTRAK AS k ON pp.KONTRAK_ID = k.KONTRAK_ID " +
                    "    LEFT JOIN " +
                    "DETAIL_PRODUKSI AS dp ON pp.PO_PRODUKSI_NO = dp.PO_PRODUKSI_NO " +
                    "    LEFT JOIN " +
                    "MASTER_PERUSAHAAN AS mp ON k.MASTER_PERUSAHAAN_ID = mp.MASTER_PERUSAHAAN_ID " +
                    "WHERE "+parameter+";";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                PoProduksiReport poProduksiReport = new PoProduksiReport();
                poProduksiReport.setPoProduksiId(resultSet.getString(1));
                poProduksiReport.setPoProduksiNo(resultSet.getString(2));
                poProduksiReport.setNoKontrak(resultSet.getString(3));
                poProduksiReport.setProduksi(resultSet.getString(4));
                poProduksiReport.setTanggal(resultSet.getString(5));
                poProduksiReport.setNilaiProduksi(resultSet.getString(6));
                poProduksiReport.setKeterangan(resultSet.getString(7));

                if (resultSet.getBlob(8) != null)
                {
                    poProduksiReport.setImagePoProduksi(resultSet.getBlob(8));
                }

                poProduksiReport.setDetailProduksiId(resultSet.getString(9));
                poProduksiReport.setMedia(resultSet.getString(10));
                poProduksiReport.setDurasi(resultSet.getString(11));
                poProduksiReport.setHari(resultSet.getString(12));
                poProduksiReport.setLokasi(resultSet.getString(13));
                poProduksiReport.setPreProduksiUraian(resultSet.getString(14));
                poProduksiReport.setPreProduksiJenis(resultSet.getString(15));
                poProduksiReport.setProduksiJenis(resultSet.getString(16));
                poProduksiReport.setProduksiJumlah(resultSet.getString(17));
                poProduksiReport.setProduksiBarang(resultSet.getString(18));
                poProduksiReport.setProduksiHargaSatuan(resultSet.getString(19));
                poProduksiReport.setProduksiTotalHarga(resultSet.getString(20));
                poProduksiReport.setPostProduksiBarang(resultSet.getString(21));
                poProduksiReport.setPostProduksiTotalHarga(resultSet.getString(22));
                allPoProduksiReport.add(poProduksiReport);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close();
        }

        return allPoProduksiReport;
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
