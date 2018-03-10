package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Kontrak
{
    private Integer kontrakId;
    private Integer listKontrakId;
    private String noKontrak;
    private Integer masterPerusahaanId;
    private String project;
    private Date date;
    private BigDecimal nilaiKontrak;
    private BigDecimal dpp;
    private BigDecimal ppn;
    private BigDecimal pph23;
    private BigDecimal sp2d;
    private Integer paid;

    public Integer getKontrakId() {
        return kontrakId;
    }

    public void setKontrakId(Integer kontrakId) {
        this.kontrakId = kontrakId;
    }

    public Integer getListKontrakId() {
        return listKontrakId;
    }

    public void setListKontrakId(Integer listKontrakId) {
        this.listKontrakId = listKontrakId;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public Integer getMasterPerusahaanId() {
        return masterPerusahaanId;
    }

    public void setMasterPerusahaanId(Integer masterPerusahaanId) {
        this.masterPerusahaanId = masterPerusahaanId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getNilaiKontrak() {
        return nilaiKontrak;
    }

    public void setNilaiKontrak(BigDecimal nilaiKontrak) {
        this.nilaiKontrak = nilaiKontrak;
    }

    public BigDecimal getDpp() {
        return dpp;
    }

    public void setDpp(BigDecimal dpp) {
        this.dpp = dpp;
    }

    public BigDecimal getPpn() {
        return ppn;
    }

    public void setPpn(BigDecimal ppn) {
        this.ppn = ppn;
    }

    public BigDecimal getPph23() {
        return pph23;
    }

    public void setPph23(BigDecimal pph23) {
        this.pph23 = pph23;
    }

    public BigDecimal getSp2d() {
        return sp2d;
    }

    public void setSp2d(BigDecimal sp2d) {
        this.sp2d = sp2d;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Kontrak{");
        sb.append("kontrakId=").append(kontrakId);
        sb.append(", listKontrakId=").append(listKontrakId);
        sb.append(", noKontrak='").append(noKontrak).append('\'');
        sb.append(", masterPerusahaanId=").append(masterPerusahaanId);
        sb.append(", project='").append(project).append('\'');
        sb.append(", date=").append(date);
        sb.append(", nilaiKontrak=").append(nilaiKontrak);
        sb.append(", dpp=").append(dpp);
        sb.append(", ppn=").append(ppn);
        sb.append(", pph23=").append(pph23);
        sb.append(", sp2d=").append(sp2d);
        sb.append(", paid=").append(paid);
        sb.append('}');
        return sb.toString();
    }
}