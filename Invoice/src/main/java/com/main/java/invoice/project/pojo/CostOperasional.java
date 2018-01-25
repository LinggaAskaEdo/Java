package com.main.java.invoice.project.pojo;

import java.util.Date;

public class CostOperasional
{
    private Integer costOperasionalId;
    private Integer masterDanaId;
    private String pic;
    private String keperluan;
    private Date tanggalPemebelian;

    public Integer getCostOperasionalId()
    {
        return costOperasionalId;
    }

    public void setCostOperasionalId(Integer costOperasionalId)
    {
        this.costOperasionalId = costOperasionalId;
    }

    public Integer getMasterDanaId()
    {
        return masterDanaId;
    }

    public void setMasterDanaId(Integer masterDanaId)
    {
        this.masterDanaId = masterDanaId;
    }

    public String getPic()
    {
        return pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public String getKeperluan()
    {
        return keperluan;
    }

    public void setKeperluan(String keperluan)
    {
        this.keperluan = keperluan;
    }

    public Date getTanggalPemebelian()
    {
        return tanggalPemebelian;
    }

    public void setTanggalPemebelian(Date tanggalPemebelian)
    {
        this.tanggalPemebelian = tanggalPemebelian;
    }

    @Override
    public String toString()
    {
        return "CostOperasional{" +
                "costOperasionalId=" + costOperasionalId +
                ", masterDanaId=" + masterDanaId +
                ", pic='" + pic + '\'' +
                ", keperluan='" + keperluan + '\'' +
                ", tanggalPemebelian=" + tanggalPemebelian +
                '}';
    }
}