package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;

public class DetailEvent
{
    private Integer detailEventId;
    private Integer poEventId;
    private String uraian;
    private String detail;
    private int vol1;
    private int vol2;
    private BigDecimal hargaSatuan;
    private BigDecimal total;

    public Integer getDetailEventId()
    {
        return detailEventId;
    }

    public void setDetailEventId(Integer detailEventId)
    {
        this.detailEventId = detailEventId;
    }

    public Integer getPoEventId()
    {
        return poEventId;
    }

    public void setPoEventId(Integer poEventId)
    {
        this.poEventId = poEventId;
    }

    public String getUraian()
    {
        return uraian;
    }

    public void setUraian(String uraian)
    {
        this.uraian = uraian;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public int getVol1()
    {
        return vol1;
    }

    public void setVol1(int vol1)
    {
        this.vol1 = vol1;
    }

    public int getVol2()
    {
        return vol2;
    }

    public void setVol2(int vol2)
    {
        this.vol2 = vol2;
    }

    public BigDecimal getHargaSatuan()
    {
        return hargaSatuan;
    }

    public void setHargaSatuan(BigDecimal hargaSatuan)
    {
        this.hargaSatuan = hargaSatuan;
    }

    public BigDecimal getTotal()
    {
        return total;
    }

    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "DetailEvent{" +
                "detailEventId=" + detailEventId +
                ", poEventId=" + poEventId +
                ", uraian='" + uraian + '\'' +
                ", detail='" + detail + '\'' +
                ", vol1=" + vol1 +
                ", vol2=" + vol2 +
                ", hargaSatuan=" + hargaSatuan +
                ", total=" + total +
                '}';
    }
}