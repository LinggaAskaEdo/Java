package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;

public class DetailProduksi
{
    private Integer detailProduksiId;
    private String poProduksiNo;
    private String media;
    private String durasi;
    private String hari;
    private String lokasi;
    private String preProduksiUraian;
    private String preProduksiJenis;
    private String produksiJenis;
    private String produksiJumlah;
    private String produksiBarang;
    private BigDecimal produksiHargaSatuan;
    private BigDecimal produksiTotalHarga;
    private String postProduksiBarang;
    private BigDecimal postProduksiTotalHarga;

    public Integer getDetailProduksiId() {
        return detailProduksiId;
    }

    public void setDetailProduksiId(Integer detailProduksiId) {
        this.detailProduksiId = detailProduksiId;
    }

    public String getPoProduksiNo() {
        return poProduksiNo;
    }

    public void setPoProduksiNo(String poProduksiNo) {
        this.poProduksiNo = poProduksiNo;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPreProduksiUraian() {
        return preProduksiUraian;
    }

    public void setPreProduksiUraian(String preProduksiUraian) {
        this.preProduksiUraian = preProduksiUraian;
    }

    public String getPreProduksiJenis() {
        return preProduksiJenis;
    }

    public void setPreProduksiJenis(String preProduksiJenis) {
        this.preProduksiJenis = preProduksiJenis;
    }

    public String getProduksiJenis() {
        return produksiJenis;
    }

    public void setProduksiJenis(String produksiJenis) {
        this.produksiJenis = produksiJenis;
    }

    public String getProduksiJumlah() {
        return produksiJumlah;
    }

    public void setProduksiJumlah(String produksiJumlah) {
        this.produksiJumlah = produksiJumlah;
    }

    public String getProduksiBarang() {
        return produksiBarang;
    }

    public void setProduksiBarang(String produksiBarang) {
        this.produksiBarang = produksiBarang;
    }

    public BigDecimal getProduksiHargaSatuan() {
        return produksiHargaSatuan;
    }

    public void setProduksiHargaSatuan(BigDecimal produksiHargaSatuan) {
        this.produksiHargaSatuan = produksiHargaSatuan;
    }

    public BigDecimal getProduksiTotalHarga() {
        return produksiTotalHarga;
    }

    public void setProduksiTotalHarga(BigDecimal produksiTotalHarga) {
        this.produksiTotalHarga = produksiTotalHarga;
    }

    public String getPostProduksiBarang() {
        return postProduksiBarang;
    }

    public void setPostProduksiBarang(String postProduksiBarang) {
        this.postProduksiBarang = postProduksiBarang;
    }

    public BigDecimal getPostProduksiTotalHarga() {
        return postProduksiTotalHarga;
    }

    public void setPostProduksiTotalHarga(BigDecimal postProduksiTotalHarga) {
        this.postProduksiTotalHarga = postProduksiTotalHarga;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DetailProduksi{");
        sb.append("detailProduksiId=").append(detailProduksiId);
        sb.append(", poProduksiNo='").append(poProduksiNo).append('\'');
        sb.append(", media='").append(media).append('\'');
        sb.append(", durasi='").append(durasi).append('\'');
        sb.append(", hari='").append(hari).append('\'');
        sb.append(", lokasi='").append(lokasi).append('\'');
        sb.append(", preProduksiUraian='").append(preProduksiUraian).append('\'');
        sb.append(", preProduksiJenis='").append(preProduksiJenis).append('\'');
        sb.append(", produksiJenis='").append(produksiJenis).append('\'');
        sb.append(", produksiJumlah='").append(produksiJumlah).append('\'');
        sb.append(", produksiBarang='").append(produksiBarang).append('\'');
        sb.append(", produksiHargaSatuan=").append(produksiHargaSatuan);
        sb.append(", produksiTotalHarga=").append(produksiTotalHarga);
        sb.append(", postProduksiBarang='").append(postProduksiBarang).append('\'');
        sb.append(", postProduksiTotalHarga=").append(postProduksiTotalHarga);
        sb.append('}');
        return sb.toString();
    }
}