package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;

public class DetailReimburse
{
    private Integer detailReimburseId;
    private Integer poEventId;
    private String uraian;
    private String detail;
    private BigDecimal harga;

    public Integer getDetailReimburseId()
    {
        return detailReimburseId;
    }

    public void setDetailReimburseId(Integer detailReimburseId)
    {
        this.detailReimburseId = detailReimburseId;
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

    public BigDecimal getHarga()
    {
        return harga;
    }

    public void setHarga(BigDecimal harga)
    {
        this.harga = harga;
    }

    @Override
    public String toString()
    {
        return "DetailReimburse{" +
                "detailReimburseId=" + detailReimburseId +
                ", poEventId=" + poEventId +
                ", uraian='" + uraian + '\'' +
                ", detail='" + detail + '\'' +
                ", harga=" + harga +
                '}';
    }
}