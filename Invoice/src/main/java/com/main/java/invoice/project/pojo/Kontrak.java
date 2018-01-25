package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Kontrak
{
    private Integer kontrakId;
    private Integer masterPerusahaanId;
    private String name;
    private String address;
    private String noNpwp;
    private String project;
    private Date date;
    private BigDecimal nilaiKontrak;
    private BigDecimal dpp;
    private BigDecimal ppn;
    private BigDecimal pph23;
    private BigDecimal sp2d;

    public Integer getKontrakId()
    {
        return kontrakId;
    }

    public void setKontrakId(Integer kontrakId)
    {
        this.kontrakId = kontrakId;
    }

    public Integer getMasterPerusahaanId()
    {
        return masterPerusahaanId;
    }

    public void setMasterPerusahaanId(Integer masterPerusahaanId)
    {
        this.masterPerusahaanId = masterPerusahaanId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getNoNpwp()
    {
        return noNpwp;
    }

    public void setNoNpwp(String noNpwp)
    {
        this.noNpwp = noNpwp;
    }

    public String getProject()
    {
        return project;
    }

    public void setProject(String project)
    {
        this.project = project;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public BigDecimal getNilaiKontrak()
    {
        return nilaiKontrak;
    }

    public void setNilaiKontrak(BigDecimal nilaiKontrak)
    {
        this.nilaiKontrak = nilaiKontrak;
    }

    public BigDecimal getDpp()
    {
        return dpp;
    }

    public void setDpp(BigDecimal dpp)
    {
        this.dpp = dpp;
    }

    public BigDecimal getPpn()
    {
        return ppn;
    }

    public void setPpn(BigDecimal ppn)
    {
        this.ppn = ppn;
    }

    public BigDecimal getPph23()
    {
        return pph23;
    }

    public void setPph23(BigDecimal pph23)
    {
        this.pph23 = pph23;
    }

    public BigDecimal getSp2d()
    {
        return sp2d;
    }

    public void setSp2d(BigDecimal sp2d)
    {
        this.sp2d = sp2d;
    }

    @Override
    public String toString()
    {
        return "Kontrak{" +
                "kontrakId=" + kontrakId +
                ", masterPerusahaanId=" + masterPerusahaanId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", noNpwp='" + noNpwp + '\'' +
                ", project='" + project + '\'' +
                ", date=" + date +
                ", nilaiKontrak=" + nilaiKontrak +
                ", dpp=" + dpp +
                ", ppn=" + ppn +
                ", pph23=" + pph23 +
                ", sp2d=" + sp2d +
                '}';
    }
}