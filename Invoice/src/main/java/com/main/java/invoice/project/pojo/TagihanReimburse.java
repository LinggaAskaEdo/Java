package com.main.java.invoice.project.pojo;

import java.util.Date;

public class TagihanReimburse
{
    private Integer tagihanReimburseId;
    private String poEventNo;
    private String poNomor;
    private String catatan;
    private Date tanggal;
    private Integer masterDanaId;
    private String keterangan;
    private String image;

    public Integer getTagihanReimburseId() {
        return tagihanReimburseId;
    }

    public void setTagihanReimburseId(Integer tagihanReimburseId) {
        this.tagihanReimburseId = tagihanReimburseId;
    }

    public String getPoEventNo() {
        return poEventNo;
    }

    public void setPoEventNo(String poEventNo) {
        this.poEventNo = poEventNo;
    }

    public String getPoNomor() {
        return poNomor;
    }

    public void setPoNomor(String poNomor) {
        this.poNomor = poNomor;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getMasterDanaId() {
        return masterDanaId;
    }

    public void setMasterDanaId(Integer masterDanaId) {
        this.masterDanaId = masterDanaId;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TagihanReimburse{");
        sb.append("tagihanReimburseId=").append(tagihanReimburseId);
        sb.append(", poEventNo='").append(poEventNo).append('\'');
        sb.append(", poNomor='").append(poNomor).append('\'');
        sb.append(", catatan='").append(catatan).append('\'');
        sb.append(", tanggal=").append(tanggal);
        sb.append(", masterDanaId=").append(masterDanaId);
        sb.append(", keterangan='").append(keterangan).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}