package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class PoProduksi
{
    private Integer poProduksiId;
    private String poProduksiNo;
    private String kontrakId; //kurangtepat
    private String produksi;
    private Date tanggal;
    private BigDecimal nilaiProduksi;
    private String keterangan;
    private String image;

    public Integer getPoProduksiId() {
        return poProduksiId;
    }

    public void setPoProduksiId(Integer poProduksiId) {
        this.poProduksiId = poProduksiId;
    }

    public String getPoProduksiNo() {
        return poProduksiNo;
    }

    public void setPoProduksiNo(String poProduksiNo) {
        this.poProduksiNo = poProduksiNo;
    }

    public String getKontrakId() {
        return kontrakId;
    }

    public void setKontrakId(String kontrakId) {
        this.kontrakId = kontrakId;
    }

    public String getProduksi() {
        return produksi;
    }

    public void setProduksi(String produksi) {
        this.produksi = produksi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getNilaiProduksi() {
        return nilaiProduksi;
    }

    public void setNilaiProduksi(BigDecimal nilaiProduksi) {
        this.nilaiProduksi = nilaiProduksi;
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
        final StringBuffer sb = new StringBuffer("PoProduksi{");
        sb.append("poProduksiId=").append(poProduksiId);
        sb.append(", poProduksiNo='").append(poProduksiNo).append('\'');
        sb.append(", kontrakId='").append(kontrakId).append('\'');
        sb.append(", produksi='").append(produksi).append('\'');
        sb.append(", tanggal=").append(tanggal);
        sb.append(", nilaiProduksi=").append(nilaiProduksi);
        sb.append(", keterangan='").append(keterangan).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}