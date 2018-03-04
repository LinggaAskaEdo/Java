package com.main.java.invoice.project.pojo;

public class MasterProduksi
{
    private Integer masterProduksiId;
    private String agentProduksi;
    private String name;
    private String address;
    private String noNpwp;
    private String information;

    public Integer getMasterProduksiId() {
        return masterProduksiId;
    }

    public void setMasterProduksiId(Integer masterProduksiId) {
        this.masterProduksiId = masterProduksiId;
    }

    public String getAgentProduksi() {
        return agentProduksi;
    }

    public void setAgentProduksi(String agentProduksi) {
        this.agentProduksi = agentProduksi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNoNpwp() {
        return noNpwp;
    }

    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MasterProduksi{");
        sb.append("masterProduksiId=").append(masterProduksiId);
        sb.append(", agentProduksi='").append(agentProduksi).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", noNpwp='").append(noNpwp).append('\'');
        sb.append(", information='").append(information).append('\'');
        sb.append('}');
        return sb.toString();
    }
}