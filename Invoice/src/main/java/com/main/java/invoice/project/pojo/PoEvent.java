package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class PoEvent
{
    private Integer poEventId;
    private String poEventNo;
    private String kontrakId; //kurangtepat
    private String kegiatan;
    private Date tanggal;
    private BigDecimal jumlah;
    private String keterangan;
    private String image;

    public Integer getPoEventId() {
        return poEventId;
    }

    public void setPoEventId(Integer poEventId) {
        this.poEventId = poEventId;
    }

    public String getPoEventNo() {
        return poEventNo;
    }

    public void setPoEventNo(String poEventNo) {
        this.poEventNo = poEventNo;
    }

    public String getKontrakId() {
        return kontrakId;
    }

    public void setKontrakId(String kontrakId) {
        this.kontrakId = kontrakId;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
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
        final StringBuffer sb = new StringBuffer("PoEvent{");
        sb.append("poEventId=").append(poEventId);
        sb.append(", poEventNo='").append(poEventNo).append('\'');
        sb.append(", kontrakId='").append(kontrakId).append('\'');
        sb.append(", kegiatan='").append(kegiatan).append('\'');
        sb.append(", tanggal=").append(tanggal);
        sb.append(", jumlah=").append(jumlah);
        sb.append(", keterangan='").append(keterangan).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}