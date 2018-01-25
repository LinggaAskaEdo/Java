package com.main.java.invoice.project.pojo;

public class MasterClient
{
    private Integer masterClientId;
    private String name;
    private String address;
    private String noNpwp;
    private String satkerPpk;
    private String information;

    public Integer getMasterClientId()
    {
        return masterClientId;
    }

    public void setMasterClientId(Integer masterClientId)
    {
        this.masterClientId = masterClientId;
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

    public String getSatkerPpk()
    {
        return satkerPpk;
    }

    public void setSatkerPpk(String satkerPpk)
    {
        this.satkerPpk = satkerPpk;
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
        return "MasterClient{" +
                "masterClientId=" + masterClientId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", noNpwp='" + noNpwp + '\'' +
                ", satkerPpk='" + satkerPpk + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}