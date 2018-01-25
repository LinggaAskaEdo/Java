package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class PoProduksi
{
    private Integer poProduksiId;
    private String poProduksiNo;
    private Integer kontrakId;
    private String produksi;
    private Date tanggal;
    private BigDecimal nilaiProduksi;
    private String keterangan;

    public Integer getPoProduksiId()
    {
        return poProduksiId;
    }

    public void setPoProduksiId(Integer poProduksiId)
    {
        this.poProduksiId = poProduksiId;
    }

    public String getPoProduksiNo()
    {
        return poProduksiNo;
    }

    public void setPoProduksiNo(String poProduksiNo)
    {
        this.poProduksiNo = poProduksiNo;
    }

    public Integer getKontrakId()
    {
        return kontrakId;
    }

    public void setKontrakId(Integer kontrakId)
    {
        this.kontrakId = kontrakId;
    }

    public String getProduksi()
    {
        return produksi;
    }

    public void setProduksi(String produksi)
    {
        this.produksi = produksi;
    }

    public Date getTanggal()
    {
        return tanggal;
    }

    public void setTanggal(Date tanggal)
    {
        this.tanggal = tanggal;
    }

    public BigDecimal getNilaiProduksi()
    {
        return nilaiProduksi;
    }

    public void setNilaiProduksi(BigDecimal nilaiProduksi)
    {
        this.nilaiProduksi = nilaiProduksi;
    }

    public String getKeterangan()
    {
        return keterangan;
    }

    public void setKeterangan(String keterangan)
    {
        this.keterangan = keterangan;
    }

    @Override
    public String toString()
    {
        return "PoProduksi{" +
                "poProduksiId=" + poProduksiId +
                ", poProduksiNo='" + poProduksiNo + '\'' +
                ", kontrakId=" + kontrakId +
                ", produksi='" + produksi + '\'' +
                ", tanggal=" + tanggal +
                ", nilaiProduksi=" + nilaiProduksi +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}