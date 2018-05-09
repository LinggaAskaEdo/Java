package com.main.java.invoice.project.pojo;

import java.sql.Blob;

/**
 * Created by dery on 5/5/18.
 */
public class FundingReport
{
    private String fundingId;
    private String kontrakName;
    private String reff;
    private String tanggal;
    private String nilai;
    private String keterangan;
    private Blob imageFunding;

    public String getFundingId() {
        return fundingId;
    }

    public void setFundingId(String fundingId) {
        this.fundingId = fundingId;
    }

    public String getKontrakName() {
        return kontrakName;
    }

    public void setKontrakName(String kontrakName) {
        this.kontrakName = kontrakName;
    }

    public String getReff() {
        return reff;
    }

    public void setReff(String reff) {
        this.reff = reff;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Blob getImageFunding() {
        return imageFunding;
    }

    public void setImageFunding(Blob imageFunding) {
        this.imageFunding = imageFunding;
    }
}
