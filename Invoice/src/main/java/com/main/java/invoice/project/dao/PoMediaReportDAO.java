package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.PoMediaReport;
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
public class PoMediaReportDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<PoMediaReport> GetAllPoMediaReportHarian (String perusahaan, String klien, Date date)
    {
        List<PoMediaReport> allPoMediaReport = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);
            String parameter = "";
            String currentDate1 = dateFormat.format(date);

            if(!perusahaan.equals("") && klien.equals(""))
            {
                parameter = "mp.NAME = "+perusahaan+" AND pm.TANGGAL_TAYANG = '" + currentDate1 + "'";
            }
            else if (perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "k.PROJECT = "+klien+" AND pm.TANGGAL_TAYANG = '" + currentDate1 + "'";
            }
            else if (!perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "mp.NAME = "+perusahaan+" AND k.PROJECT = "+klien+" AND pm.TANGGAL_TAYANG = '" + currentDate1 + "'";
            }
            else
            {
                parameter = "pm.TANGGAL_TAYANG = '" + currentDate1 + "'";
            }

            String query = "SELECT " +
                     "k.NO_KONTRAK, " +
                     "mm.MEDIA_NAME, " +
                     "pm.PO_MEDIA_ID, " +
                     "pm.PO_MEDIA_NO, " +
                     "pm.PEKERJAAN_KEMENTERIAN, " +
                     "pm.TANGGAL_TAYANG, " +
                     "pm.UKURAN, " +
                     "pm.HARGA, " +
                     "pm.PPN, " +
                     "pm.KETERANGAN, " +
                     "TO_BASE64(pm.PPN_IMAGE) AS IMAGE, " +
                     "tm.TAGIHAN_MEDIA_ID, " +
                     "tm.INVOICE_MEDIA, " +
                     "tm.TANGGAL, " +
                     "tm.NILAI_TAGIHAN, " +
                     "TO_BASE64(tm.PPN_IMAGE) " +
                    "FROM " +
                     "PO_MEDIA pm " +
                     "    INNER JOIN " +
                     "KONTRAK k ON pm.KONTRAK_ID = k.KONTRAK_ID " +
                     "    INNER JOIN " +
                     "MASTER_MEDIA mm ON pm.MASTER_MEDIA_ID = mm.MASTER_MEDIA_ID " +
                     "    INNER JOIN " +
                     "TAGIHAN_MEDIA tm ON pm.PO_MEDIA_NO = tm.PO_MEDIA_NO " +
                     "    INNER JOIN " +
                     "MASTER_PERUSAHAAN mp ON k.MASTER_PERUSAHAAN_ID = mp.MASTER_PERUSAHAAN_ID " +
                    "WHERE "+parameter+";";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                PoMediaReport poMediaReport = new PoMediaReport();
                poMediaReport.setNoKontrak(resultSet.getString(1));
                poMediaReport.setMediaName(resultSet.getString(2));
                poMediaReport.setPoMediaId(resultSet.getString(3));
                poMediaReport.setPoMediaNo(resultSet.getString(4));
                poMediaReport.setPekerjaanKementerian(resultSet.getString(5));
                poMediaReport.setTanggalTayang(resultSet.getString(6));
                poMediaReport.setUkuran(resultSet.getString(7));
                poMediaReport.setHarga(resultSet.getString(8));
                poMediaReport.setPpn(resultSet.getString(9));
                poMediaReport.setKeterangan(resultSet.getString(10));
                poMediaReport.setImagePoMedia(resultSet.getString(11));
                poMediaReport.setTagihanMediaId(resultSet.getString(12));
                poMediaReport.setInvoiceMedia(resultSet.getString(13));
                poMediaReport.setTanggal(resultSet.getString(14));
                poMediaReport.setNilaiTagihan(resultSet.getString(15));
                poMediaReport.setImageTagihanMedia(resultSet.getString(16));
                allPoMediaReport.add(poMediaReport);
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

        return allPoMediaReport;
    }

    public List<PoMediaReport> GetAllPoMediaReportBulanan (String perusahaan, String klien, Date date1, Date date2)
    {
        List<PoMediaReport> allPoMediaReport = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);
            String parameter = "";
            String currentDate1 = dateFormat.format(date1);
            String currentDate2 = dateFormat.format(date2);

            if(!perusahaan.equals("") && klien.equals(""))
            {
                parameter = "mp.NAME = "+perusahaan+" AND pm.TANGGAL_TAYANG BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else if (perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "k.PROJECT = "+klien+" AND pm.TANGGAL_TAYANG BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else if (!perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "mp.NAME = "+perusahaan+" AND k.PROJECT = "+klien+" AND pm.TANGGAL_TAYANG BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else
            {
                parameter = "pm.TANGGAL_TAYANG BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }

            String query = "SELECT " +
                    "k.NO_KONTRAK, " +
                    "mm.MEDIA_NAME, " +
                    "pm.PO_MEDIA_ID, " +
                    "pm.PO_MEDIA_NO, " +
                    "pm.PEKERJAAN_KEMENTERIAN, " +
                    "pm.TANGGAL_TAYANG, " +
                    "pm.UKURAN, " +
                    "pm.HARGA, " +
                    "pm.PPN, " +
                    "pm.KETERANGAN, " +
                    "TO_BASE64(pm.PPN_IMAGE) AS IMAGE, " +
                    "tm.TAGIHAN_MEDIA_ID, " +
                    "tm.INVOICE_MEDIA, " +
                    "tm.TANGGAL, " +
                    "tm.NILAI_TAGIHAN, " +
                    "TO_BASE64(tm.PPN_IMAGE) " +
                    "FROM " +
                    "PO_MEDIA pm " +
                    "    INNER JOIN " +
                    "KONTRAK k ON pm.KONTRAK_ID = k.KONTRAK_ID " +
                    "    INNER JOIN " +
                    "MASTER_MEDIA mm ON pm.MASTER_MEDIA_ID = mm.MASTER_MEDIA_ID " +
                    "    INNER JOIN " +
                    "TAGIHAN_MEDIA tm ON pm.PO_MEDIA_NO = tm.PO_MEDIA_NO " +
                    "    INNER JOIN " +
                    "MASTER_PERUSAHAAN mp ON k.MASTER_PERUSAHAAN_ID = mp.MASTER_PERUSAHAAN_ID " +
                    "WHERE "+parameter+";";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                PoMediaReport poMediaReport = new PoMediaReport();
                poMediaReport.setNoKontrak(resultSet.getString(1));
                poMediaReport.setMediaName(resultSet.getString(2));
                poMediaReport.setPoMediaId(resultSet.getString(3));
                poMediaReport.setPoMediaNo(resultSet.getString(4));
                poMediaReport.setPekerjaanKementerian(resultSet.getString(5));
                poMediaReport.setTanggalTayang(resultSet.getString(6));
                poMediaReport.setUkuran(resultSet.getString(7));
                poMediaReport.setHarga(resultSet.getString(8));
                poMediaReport.setPpn(resultSet.getString(9));
                poMediaReport.setKeterangan(resultSet.getString(10));
                poMediaReport.setImagePoMedia(resultSet.getString(11));
                poMediaReport.setTagihanMediaId(resultSet.getString(12));
                poMediaReport.setInvoiceMedia(resultSet.getString(13));
                poMediaReport.setTanggal(resultSet.getString(14));
                poMediaReport.setNilaiTagihan(resultSet.getString(15));
                poMediaReport.setImageTagihanMedia(resultSet.getString(16));
                allPoMediaReport.add(poMediaReport);
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

        return allPoMediaReport;
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
