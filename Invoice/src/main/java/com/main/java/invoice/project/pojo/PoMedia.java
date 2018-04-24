package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class PoMedia
{
    private Integer poMediaId;
    private String poMediaNo;
    private Integer kontrakId;
    private String pekerjaanKementerian;
    private Integer masterMediaId;
    private Date tanggalTayang;
    private String ukuran;
    private BigDecimal harga;
    private BigDecimal ppn;
    private String keterangan;
    private String image;

    public Integer getPoMediaId() {
        return poMediaId;
    }

    public void setPoMediaId(Integer poMediaId) {
        this.poMediaId = poMediaId;
    }

    public String getPoMediaNo() {
        return poMediaNo;
    }

    public void setPoMediaNo(String poMediaNo) {
        this.poMediaNo = poMediaNo;
    }

    public Integer getKontrakId() {
        return kontrakId;
    }

    public void setKontrakId(Integer kontrakId) {
        this.kontrakId = kontrakId;
    }

    public String getPekerjaanKementerian() {
        return pekerjaanKementerian;
    }

    public void setPekerjaanKementerian(String pekerjaanKementerian) {
        this.pekerjaanKementerian = pekerjaanKementerian;
    }

    public Integer getMasterMediaId() {
        return masterMediaId;
    }

    public void setMasterMediaId(Integer masterMediaId) {
        this.masterMediaId = masterMediaId;
    }

    public Date getTanggalTayang() {
        return tanggalTayang;
    }

    public void setTanggalTayang(Date tanggalTayang) {
        this.tanggalTayang = tanggalTayang;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public BigDecimal getPpn() {
        return ppn;
    }

    public void setPpn(BigDecimal ppn) {
        this.ppn = ppn;
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
    public String toString()
    {
        return "PoMedia{" + "poMediaId=" + poMediaId +
                ", poMediaNo='" + poMediaNo + '\'' +
                ", kontrakId=" + kontrakId +
                ", pekerjaanKementerian='" + pekerjaanKementerian + '\'' +
                ", masterMediaId=" + masterMediaId +
                ", tanggalTayang=" + tanggalTayang +
                ", ukuran='" + ukuran + '\'' +
                ", harga=" + harga +
                ", ppn=" + ppn +
                ", keterangan='" + keterangan + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}