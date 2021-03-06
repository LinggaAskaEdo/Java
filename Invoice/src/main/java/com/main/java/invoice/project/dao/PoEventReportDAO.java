package com.main.java.invoice.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.main.java.invoice.project.pojo.PoEventReport;
import com.main.java.invoice.project.preference.StaticPreference;

/**
 * Created by dery on 5/8/18.
 */
public class PoEventReportDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<PoEventReport> GetAllPoEventReportHarian (String perusahaan, String klien, Date date)
    {
        List<PoEventReport> allPoEventReport = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);
            String parameter;
            String currentDate1 = dateFormat.format(date);

            if(!perusahaan.equals("") && klien.equals(""))
            {
                parameter = "mp.NAME = '"+perusahaan+"' AND pe.TANGGAL = '" + currentDate1 + "'";
            }
            else if (perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "k.PROJECT = '"+klien+"' AND pe.TANGGAL = '" + currentDate1 + "'";
            }
            else if (!perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "mp.NAME = '"+perusahaan+"' OR k.PROJECT = '"+klien+"' AND pe.TANGGAL = '" + currentDate1 + "'";
            }
            else
            {
                parameter = "pe.TANGGAL = '" + currentDate1 + "'";
            }

            String query = "SELECT " +
                    "pe.PO_EVENT_ID, " +
                    "pe.PO_EVENT_NO, " +
                    "k.NO_KONTRAK, " +
                    "pe.KEGIATAN, " +
                    "pe.TANGGAL, " +
                    "pe.JUMLAH, " +
                    "pe.KETERANGAN, " +
                    "pe.BUKTI, " +
                    "de.DETAIL_EVENT_ID, " +
                    "de.URAIAN, " +
                    "de.DETAIL, " +
                    "de.VOL_1, " +
                    "de.JENIS_1, " +
                    "de.VOL_2, " +
                    "de.JENIS_2, " +
                    "de.HARGA_SATUAN, " +
                    "de.TOTAL, " +
                    "dr.URAIAN AS URAIA, " +
                    "dr.DETAIL AS DETAI, " +
                    "dr.HARGA AS HARG, " +
                    "tr.TAGIHAN_REIMBURSE_ID, " +
                    "tr.PO_NOMOR, " +
                    "tr.CATATAN, " +
                    "tr.TANGGAL AS TANGGAL_TR, " +
                    "tr.MASTER_MEDIA_ID, " +
                    "tr.KETERANGAN, " +
                    "tr.PPN_IMAGE AS IMAGE_TR " +
                    "FROM " +
                    "PO_EVENT pe " +
                    "    LEFT JOIN " +
                    "KONTRAK k ON pe.KONTRAK_ID = k.KONTRAK_ID " +
                    "    LEFT JOIN " +
                    "DETAIL_EVENT de ON pe.PO_EVENT_NO = de.PO_EVENT_NO " +
                    "    LEFT JOIN " +
                    "DETAIL_REIMBURSE dr ON pe.PO_EVENT_NO = dr.PO_EVENT_NO " +
                    "    LEFT JOIN " +
                    "TAGIHAN_REIMBURSE tr ON pe.PO_EVENT_NO = tr.PO_EVENT_NO " +
                    "    LEFT JOIN " +
                    "MASTER_PERUSAHAAN mp ON k.MASTER_PERUSAHAAN_ID = mp.MASTER_PERUSAHAAN_ID " +
                    "WHERE "+parameter+";";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                PoEventReport poEventReport = new PoEventReport();
                poEventReport.setPoEventId(resultSet.getString(1));
                poEventReport.setPoEventNo(resultSet.getString(2));
                poEventReport.setNoKontrak(resultSet.getString(3));
                poEventReport.setKegiatan(resultSet.getString(4));
                poEventReport.setTanggal(resultSet.getString(5));
                if(resultSet.getString(6) != null)
                {
                    poEventReport.setJumlah(changeFormat(resultSet.getString(6)));
                }
                poEventReport.setKeterangan(resultSet.getString(7));

                if (resultSet.getBlob(8) != null)
                {
                    poEventReport.setBukti(resultSet.getBlob(8));
                }

                poEventReport.setDetailEventId(resultSet.getString(9));
                poEventReport.setUraian(resultSet.getString(10));
                poEventReport.setDetail(resultSet.getString(11));
                poEventReport.setVol1(resultSet.getString(12));
                poEventReport.setJenis1(resultSet.getString(13));
                poEventReport.setVol2(resultSet.getString(14));
                poEventReport.setJenis2(resultSet.getString(15));
                if(resultSet.getString(16) != null)
                {
                    poEventReport.setHargaSatuan(changeFormat(resultSet.getString(16)));
                }
                if(resultSet.getString(17) != null)
                {
                    poEventReport.setTotal(changeFormat(resultSet.getString(17)));
                }
                if(resultSet.getString(18) != null)
                {
                    poEventReport.setUraianDr(changeFormat(resultSet.getString(18)));
                }
                poEventReport.setDetailDr(resultSet.getString(19));

                if(resultSet.getString(20) != null)
                {
                    poEventReport.setHargaDr(changeFormat(resultSet.getString(20)));
                }

                poEventReport.setTagihanReimbuseIdTr(resultSet.getString(21));
                poEventReport.setPoNomorTr(resultSet.getString(22));
                poEventReport.setCatatanTr(resultSet.getString(23));
                poEventReport.setTanggalTr(resultSet.getString(24));
                poEventReport.setMasterMediaId(resultSet.getString(25));
                poEventReport.setKeteranganTr(resultSet.getString(26));

                if (resultSet.getBlob(27) != null)
                {
                    poEventReport.setImageTr(resultSet.getBlob(27));
                }

                allPoEventReport.add(poEventReport);
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

        return allPoEventReport;
    }

    public List<PoEventReport> GetAllPoEventReportBulanan (String perusahaan, String klien, Date date1, Date date2)
    {
        List<PoEventReport> allPoEventReport = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);
            String parameter;
            String currentDate1 = dateFormat.format(date1);
            String currentDate2 = dateFormat.format(date2);

            if(!perusahaan.equals("") && klien.equals(""))
            {
                parameter = "mp.NAME = '"+perusahaan+"' AND pe.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else if (perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "k.PROJECT = '"+klien+"' AND pe.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else if (!perusahaan.equals("") && !klien.equals(""))
            {
                parameter = "mp.NAME = '"+perusahaan+"' OR k.PROJECT = '"+klien+"' AND pe.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }
            else
            {
                parameter = "pe.TANGGAL BETWEEN '" + currentDate1 + "' AND '" + currentDate2 + "'";
            }

            String query = "SELECT " +
                    "pe.PO_EVENT_ID, " +
                    "pe.PO_EVENT_NO, " +
                    "k.NO_KONTRAK, " +
                    "pe.KEGIATAN, " +
                    "pe.TANGGAL, " +
                    "pe.JUMLAH, " +
                    "pe.KETERANGAN, " +
                    "pe.BUKTI, " +
                    "de.DETAIL_EVENT_ID, " +
                    "de.URAIAN, " +
                    "de.DETAIL, " +
                    "de.VOL_1, " +
                    "de.JENIS_1, " +
                    "de.VOL_2, " +
                    "de.JENIS_2, " +
                    "de.HARGA_SATUAN, " +
                    "de.TOTAL, " +
                    "dr.URAIAN AS URAIA, " +
                    "dr.DETAIL AS DETAI, " +
                    "dr.HARGA AS HARG, " +
                    "tr.TAGIHAN_REIMBURSE_ID, " +
                    "tr.PO_NOMOR, " +
                    "tr.CATATAN, " +
                    "tr.TANGGAL AS TANGGAL_TR, " +
                    "tr.MASTER_MEDIA_ID, " +
                    "tr.KETERANGAN, " +
                    "tr.PPN_IMAGE AS IMAGE_TR " +
                    "FROM " +
                    "PO_EVENT pe " +
                    "    LEFT JOIN " +
                    "KONTRAK k ON pe.KONTRAK_ID = k.KONTRAK_ID " +
                    "    LEFT JOIN " +
                    "DETAIL_EVENT de ON pe.PO_EVENT_NO = de.PO_EVENT_NO " +
                    "    LEFT JOIN " +
                    "DETAIL_REIMBURSE dr ON pe.PO_EVENT_NO = dr.PO_EVENT_NO " +
                    "    LEFT JOIN " +
                    "TAGIHAN_REIMBURSE tr ON pe.PO_EVENT_NO = tr.PO_EVENT_NO " +
                    "    LEFT JOIN " +
                    "MASTER_PERUSAHAAN mp ON k.MASTER_PERUSAHAAN_ID = mp.MASTER_PERUSAHAAN_ID " +
                    "WHERE "+parameter+";";

            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                PoEventReport poEventReport = new PoEventReport();
                poEventReport.setPoEventId(resultSet.getString(1));
                poEventReport.setPoEventNo(resultSet.getString(2));
                poEventReport.setNoKontrak(resultSet.getString(3));
                poEventReport.setKegiatan(resultSet.getString(4));
                poEventReport.setTanggal(resultSet.getString(5));

                if(resultSet.getString(6) != null)
                {
                    poEventReport.setJumlah(changeFormat(resultSet.getString(6)));
                }
                poEventReport.setKeterangan(resultSet.getString(7));

                if (resultSet.getBlob(8) != null)
                {
                    poEventReport.setBukti(resultSet.getBlob(8));
                }

                poEventReport.setDetailEventId(resultSet.getString(9));
                poEventReport.setUraian(resultSet.getString(10));
                poEventReport.setDetail(resultSet.getString(11));
                poEventReport.setVol1(resultSet.getString(12));
                poEventReport.setJenis1(resultSet.getString(13));
                poEventReport.setVol2(resultSet.getString(14));
                poEventReport.setJenis2(resultSet.getString(15));

                if(resultSet.getString(16) != null)
                {
                    poEventReport.setHargaSatuan(changeFormat(resultSet.getString(16)));
                }
                if(resultSet.getString(17) != null)
                {
                    poEventReport.setTotal(changeFormat(resultSet.getString(17)));
                }
                if(resultSet.getString(18) != null)
                {
                    poEventReport.setUraianDr(changeFormat(resultSet.getString(18)));
                }
                poEventReport.setDetailDr(resultSet.getString(19));

                if (resultSet.getString(20) != null)
                {
                    poEventReport.setHargaDr(changeFormat(resultSet.getString(20)));
                }

                poEventReport.setTagihanReimbuseIdTr(resultSet.getString(21));
                poEventReport.setPoNomorTr(resultSet.getString(22));
                poEventReport.setCatatanTr(resultSet.getString(23));
                poEventReport.setTanggalTr(resultSet.getString(24));
                poEventReport.setMasterMediaId(resultSet.getString(25));
                poEventReport.setKeteranganTr(resultSet.getString(26));

                if (resultSet.getBlob(27) != null)
                {
                    poEventReport.setImageTr(resultSet.getBlob(27));
                }

                allPoEventReport.add(poEventReport);
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

        return allPoEventReport;
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

    private String changeFormat(String data)
    {
        NumberFormat numbFormat = new DecimalFormat("#,###.00");

        double changeData = Double.parseDouble(data);

        return numbFormat.format(changeData);
    }
}
