package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Funding
{
    private Integer fundingId;
    private Integer kontrakId;
    private Date tanggal;
    private BigDecimal nilai;
    private String keterangan;

    public Integer getFundingId()
    {
        return fundingId;
    }

    public void setFundingId(Integer fundingId)
    {
        this.fundingId = fundingId;
    }

    public Integer getKontrakId()
    {
        return kontrakId;
    }

    public void setKontrakId(Integer kontrakId)
    {
        this.kontrakId = kontrakId;
    }

    public Date getTanggal()
    {
        return tanggal;
    }

    public void setTanggal(Date tanggal)
    {
        this.tanggal = tanggal;
    }

    public BigDecimal getNilai()
    {
        return nilai;
    }

    public void setNilai(BigDecimal nilai)
    {
        this.nilai = nilai;
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
        return "Funding{" +
                "fundingId=" + fundingId +
                ", kontrakId=" + kontrakId +
                ", tanggal=" + tanggal +
                ", nilai=" + nilai +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}