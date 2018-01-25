package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;

public class DetailProduksi
{
    private Integer detailProduksiId;
    private Integer poProduksiId;
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

    public Integer getDetailProduksiId()
    {
        return detailProduksiId;
    }

    public void setDetailProduksiId(Integer detailProduksiId)
    {
        this.detailProduksiId = detailProduksiId;
    }

    public Integer getPoProduksiId()
    {
        return poProduksiId;
    }

    public void setPoProduksiId(Integer poProduksiId)
    {
        this.poProduksiId = poProduksiId;
    }

    public String getMedia()
    {
        return media;
    }

    public void setMedia(String media)
    {
        this.media = media;
    }

    public String getDurasi()
    {
        return durasi;
    }

    public void setDurasi(String durasi)
    {
        this.durasi = durasi;
    }

    public String getHari()
    {
        return hari;
    }

    public void setHari(String hari)
    {
        this.hari = hari;
    }

    public String getLokasi()
    {
        return lokasi;
    }

    public void setLokasi(String lokasi)
    {
        this.lokasi = lokasi;
    }

    public String getPreProduksiUraian()
    {
        return preProduksiUraian;
    }

    public void setPreProduksiUraian(String preProduksiUraian)
    {
        this.preProduksiUraian = preProduksiUraian;
    }

    public String getPreProduksiJenis()
    {
        return preProduksiJenis;
    }

    public void setPreProduksiJenis(String preProduksiJenis)
    {
        this.preProduksiJenis = preProduksiJenis;
    }

    public String getProduksiJenis()
    {
        return produksiJenis;
    }

    public void setProduksiJenis(String produksiJenis)
    {
        this.produksiJenis = produksiJenis;
    }

    public String getProduksiJumlah()
    {
        return produksiJumlah;
    }

    public void setProduksiJumlah(String produksiJumlah)
    {
        this.produksiJumlah = produksiJumlah;
    }

    public String getProduksiBarang()
    {
        return produksiBarang;
    }

    public void setProduksiBarang(String produksiBarang)
    {
        this.produksiBarang = produksiBarang;
    }

    public BigDecimal getProduksiHargaSatuan()
    {
        return produksiHargaSatuan;
    }

    public void setProduksiHargaSatuan(BigDecimal produksiHargaSatuan)
    {
        this.produksiHargaSatuan = produksiHargaSatuan;
    }

    public BigDecimal getProduksiTotalHarga()
    {
        return produksiTotalHarga;
    }

    public void setProduksiTotalHarga(BigDecimal produksiTotalHarga)
    {
        this.produksiTotalHarga = produksiTotalHarga;
    }

    public String getPostProduksiBarang()
    {
        return postProduksiBarang;
    }

    public void setPostProduksiBarang(String postProduksiBarang)
    {
        this.postProduksiBarang = postProduksiBarang;
    }

    public BigDecimal getPostProduksiTotalHarga()
    {
        return postProduksiTotalHarga;
    }

    public void setPostProduksiTotalHarga(BigDecimal postProduksiTotalHarga)
    {
        this.postProduksiTotalHarga = postProduksiTotalHarga;
    }

    @Override
    public String toString()
    {
        return "DetailProduksi{" +
                "detailProduksiId=" + detailProduksiId +
                ", poProduksiId=" + poProduksiId +
                ", media='" + media + '\'' +
                ", durasi='" + durasi + '\'' +
                ", hari='" + hari + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", preProduksiUraian='" + preProduksiUraian + '\'' +
                ", preProduksiJenis='" + preProduksiJenis + '\'' +
                ", produksiJenis='" + produksiJenis + '\'' +
                ", produksiJumlah='" + produksiJumlah + '\'' +
                ", produksiBarang='" + produksiBarang + '\'' +
                ", produksiHargaSatuan=" + produksiHargaSatuan +
                ", produksiTotalHarga=" + produksiTotalHarga +
                ", postProduksiBarang='" + postProduksiBarang + '\'' +
                ", postProduksiTotalHarga=" + postProduksiTotalHarga +
                '}';
    }
}