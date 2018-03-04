package com.main.java.invoice.project.pojo;

import java.awt.*;
import java.sql.Blob;

public class MasterMedia
{
    private Integer masterMediaId;
    private String companyName;
    private String mediaName;
    private String address;
    private String noNpwp;
    private String billCommitment;
    private String information;

    public Integer getMasterMediaId() {
        return masterMediaId;
    }

    public void setMasterMediaId(Integer masterMediaId) {
        this.masterMediaId = masterMediaId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
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

    public String getBillCommitment() {
        return billCommitment;
    }

    public void setBillCommitment(String billCommitment) {
        this.billCommitment = billCommitment;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MasterMedia{");
        sb.append("masterMediaId=").append(masterMediaId);
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", mediaName='").append(mediaName).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", noNpwp='").append(noNpwp).append('\'');
        sb.append(", billCommitment='").append(billCommitment).append('\'');
        sb.append(", information='").append(information).append('\'');
        sb.append('}');
        return sb.toString();
    }
}