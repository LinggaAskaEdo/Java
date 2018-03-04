package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;

public class DetailEvent
{
    private Integer detailEventId;
    private String poEventNo;
    private String uraian;
    private String detail;
    private int vol1;
    private String jenis1;
    private int vol2;
    private String jenis2;
    private BigDecimal hargaSatuan;
    private BigDecimal total;

    public Integer getDetailEventId() {
        return detailEventId;
    }

    public void setDetailEventId(Integer detailEventId) {
        this.detailEventId = detailEventId;
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

    public int getVol1() {
        return vol1;
    }

    public void setVol1(int vol1) {
        this.vol1 = vol1;
    }

    public String getJenis1() {
        return jenis1;
    }

    public void setJenis1(String jenis1) {
        this.jenis1 = jenis1;
    }

    public int getVol2() {
        return vol2;
    }

    public void setVol2(int vol2) {
        this.vol2 = vol2;
    }

    public String getJenis2() {
        return jenis2;
    }

    public void setJenis2(String jenis2) {
        this.jenis2 = jenis2;
    }

    public BigDecimal getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(BigDecimal hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DetailEvent{");
        sb.append("detailEventId=").append(detailEventId);
        sb.append(", poEventNo='").append(poEventNo).append('\'');
        sb.append(", uraian='").append(uraian).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", vol1=").append(vol1);
        sb.append(", jenis1='").append(jenis1).append('\'');
        sb.append(", vol2=").append(vol2);
        sb.append(", jenis2='").append(jenis2).append('\'');
        sb.append(", hargaSatuan=").append(hargaSatuan);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}