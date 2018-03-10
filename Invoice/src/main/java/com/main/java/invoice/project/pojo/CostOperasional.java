package com.main.java.invoice.project.pojo;

import java.util.Date;

public class CostOperasional
{
    private Integer costOperasionalId;
    private String masterDana; //kurangtepat
    private String pic;
    private String keperluan;
    private Date tanggalPemebelian;
    private String image;

    public Integer getCostOperasionalId() {
        return costOperasionalId;
    }

    public void setCostOperasionalId(Integer costOperasionalId) {
        this.costOperasionalId = costOperasionalId;
    }

    public String getMasterDana() {
        return masterDana;
    }

    public void setMasterDana(String masterDana) {
        this.masterDana = masterDana;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public Date getTanggalPemebelian() {
        return tanggalPemebelian;
    }

    public void setTanggalPemebelian(Date tanggalPemebelian) {
        this.tanggalPemebelian = tanggalPemebelian;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CostOperasional{");
        sb.append("costOperasionalId=").append(costOperasionalId);
        sb.append(", masterDana='").append(masterDana).append('\'');
        sb.append(", pic='").append(pic).append('\'');
        sb.append(", keperluan='").append(keperluan).append('\'');
        sb.append(", tanggalPemebelian=").append(tanggalPemebelian);
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}