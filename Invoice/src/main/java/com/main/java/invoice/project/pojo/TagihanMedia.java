package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class TagihanMedia
{
    private Integer tagihanMediaId;
    private String poMediaNo;
    private String invoiceMedia;
    private Date tanggal;
    private BigDecimal nilaiTagihan;
    private Integer masterDanaId;
    private String image;

    public Integer getTagihanMediaId() {
        return tagihanMediaId;
    }

    public void setTagihanMediaId(Integer tagihanMediaId) {
        this.tagihanMediaId = tagihanMediaId;
    }

    public String getPoMediaNo() {
        return poMediaNo;
    }

    public void setPoMediaNo(String poMediaNo) {
        this.poMediaNo = poMediaNo;
    }

    public String getInvoiceMedia() {
        return invoiceMedia;
    }

    public void setInvoiceMedia(String invoiceMedia) {
        this.invoiceMedia = invoiceMedia;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getNilaiTagihan() {
        return nilaiTagihan;
    }

    public void setNilaiTagihan(BigDecimal nilaiTagihan) {
        this.nilaiTagihan = nilaiTagihan;
    }

    public Integer getMasterDanaId() {
        return masterDanaId;
    }

    public void setMasterDanaId(Integer masterDanaId) {
        this.masterDanaId = masterDanaId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TagihanMedia{");
        sb.append("tagihanMediaId=").append(tagihanMediaId);
        sb.append(", poMediaNo='").append(poMediaNo).append('\'');
        sb.append(", invoiceMedia='").append(invoiceMedia).append('\'');
        sb.append(", tanggal=").append(tanggal);
        sb.append(", nilaiTagihan=").append(nilaiTagihan);
        sb.append(", masterDanaId=").append(masterDanaId);
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}