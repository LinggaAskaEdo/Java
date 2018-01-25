package com.main.java.invoice.project.pojo;

import java.util.Date;

public class TagihanReimburse
{
    private Integer tagihanReimburseId;
    private String catatan;
    private Date tanggal;
    private Integer fundingId;
    private String keterangan;

    public Integer getTagihanReimburseId()
    {
        return tagihanReimburseId;
    }

    public void setTagihanReimburseId(Integer tagihanReimburseId)
    {
        this.tagihanReimburseId = tagihanReimburseId;
    }

    public String getCatatan()
    {
        return catatan;
    }

    public void setCatatan(String catatan)
    {
        this.catatan = catatan;
    }

    public Date getTanggal()
    {
        return tanggal;
    }

    public void setTanggal(Date tanggal)
    {
        this.tanggal = tanggal;
    }

    public Integer getFundingId()
    {
        return fundingId;
    }

    public void setFundingId(Integer fundingId)
    {
        this.fundingId = fundingId;
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
        return "TagihanReimburse{" +
                "tagihanReimburseId=" + tagihanReimburseId +
                ", catatan='" + catatan + '\'' +
                ", tanggal=" + tanggal +
                ", fundingId=" + fundingId +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}