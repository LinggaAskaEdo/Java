package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;

public class DetailReimburse
{
    private Integer detailReimburseId;
    private String poEventNo;
    private String uraian;
    private String detail;
    private BigDecimal harga;

    public Integer getDetailReimburseId() {
        return detailReimburseId;
    }

    public void setDetailReimburseId(Integer detailReimburseId) {
        this.detailReimburseId = detailReimburseId;
    }

    public String getPoEventNo() {
        return poEventNo;
    }

    public void setPoEventNo(String poEventNo) {
        this.poEventNo = poEventNo;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DetailReimburse{");
        sb.append("detailReimburseId=").append(detailReimburseId);
        sb.append(", poEventNo='").append(poEventNo).append('\'');
        sb.append(", uraian='").append(uraian).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", harga=").append(harga);
        sb.append('}');
        return sb.toString();
    }
}