package com.main.java.invoice.project.pojo;

import java.sql.Blob;

/**
 * Created by dery on 5/7/18.
 */
public class PoMediaReport
{
    private String noKontrak;
    private String mediaName;
    private String poMediaId;
    private String poMediaNo;
    private String pekerjaanKementerian;
    private String tanggalTayang;
    private String ukuran;
    private String harga;
    private String ppn;
    private String keterangan;
    private Blob imagePoMedia;
    private String tagihanMediaId;
    private String invoiceMedia;
    private String tanggal;
    private String nilaiTagihan;
    private Blob imageTagihanMedia;

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getPoMediaId() {
        return poMediaId;
    }

    public void setPoMediaId(String poMediaId) {
        this.poMediaId = poMediaId;
    }

    public String getPoMediaNo() {
        return poMediaNo;
    }

    public void setPoMediaNo(String poMediaNo) {
        this.poMediaNo = poMediaNo;
    }

    public String getPekerjaanKementerian() {
        return pekerjaanKementerian;
    }

    public void setPekerjaanKementerian(String pekerjaanKementerian) {
        this.pekerjaanKementerian = pekerjaanKementerian;
    }

    public String getTanggalTayang() {
        return tanggalTayang;
    }

    public void setTanggalTayang(String tanggalTayang) {
        this.tanggalTayang = tanggalTayang;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPpn() {
        return ppn;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Blob getImagePoMedia() {
        return imagePoMedia;
    }

    public void setImagePoMedia(Blob imagePoMedia) {
        this.imagePoMedia = imagePoMedia;
    }

    public String getTagihanMediaId() {
        return tagihanMediaId;
    }

    public void setTagihanMediaId(String tagihanMediaId) {
        this.tagihanMediaId = tagihanMediaId;
    }

    public String getInvoiceMedia() {
        return invoiceMedia;
    }

    public void setInvoiceMedia(String invoiceMedia) {
        this.invoiceMedia = invoiceMedia;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNilaiTagihan() {
        return nilaiTagihan;
    }

    public void setNilaiTagihan(String nilaiTagihan) {
        this.nilaiTagihan = nilaiTagihan;
    }

    public Blob getImageTagihanMedia() {
        return imageTagihanMedia;
    }

    public void setImageTagihanMedia(Blob imageTagihanMedia) {
        this.imageTagihanMedia = imageTagihanMedia;
    }
}
