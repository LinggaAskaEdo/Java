package com.main.java.invoice.project.pojo;

public class MasterPerusahaan
{
    private Integer masterPerusahaanId;
    private String code;
    private String name;
    private String image;
    private String address;
    private String noNpwp;
    private String contactNumber;
    private String noBankAccount;
    private String feeAgency;

    public Integer getMasterPerusahaanId() {
        return masterPerusahaanId;
    }

    public void setMasterPerusahaanId(Integer masterPerusahaanId) {
        this.masterPerusahaanId = masterPerusahaanId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getNoBankAccount() {
        return noBankAccount;
    }

    public void setNoBankAccount(String noBankAccount) {
        this.noBankAccount = noBankAccount;
    }

    public String getFeeAgency() {
        return feeAgency;
    }

    public void setFeeAgency(String feeAgency) {
        this.feeAgency = feeAgency;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MasterPerusahaan{");
        sb.append("masterPerusahaanId=").append(masterPerusahaanId);
        sb.append(", code='").append(code).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", noNpwp='").append(noNpwp).append('\'');
        sb.append(", contactNumber='").append(contactNumber).append('\'');
        sb.append(", noBankAccount='").append(noBankAccount).append('\'');
        sb.append(", feeAgency='").append(feeAgency).append('\'');
        sb.append('}');
        return sb.toString();
    }
}