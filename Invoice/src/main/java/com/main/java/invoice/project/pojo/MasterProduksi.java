package com.main.java.invoice.project.pojo;

public class MasterProduksi
{
    private Integer masterProduksiId;
    private String name;
    private String address;
    private String noNpwp;
    private String information;

    public Integer getMasterProduksiId()
    {
        return masterProduksiId;
    }

    public void setMasterProduksiId(Integer masterProduksiId)
    {
        this.masterProduksiId = masterProduksiId;
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

    public String getInformation()
    {
        return information;
    }

    public void setInformation(String information)
    {
        this.information = information;
    }

    @Override
    public String toString()
    {
        return "MasterProduksi{" +
                "masterProduksiId=" + masterProduksiId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", noNpwp='" + noNpwp + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}