package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;

public class MasterDana
{
    private Integer masterDanaId;
    private String nameBankAccount;
    private String noBankAccount;
    private String nameAccount;
    private BigDecimal totalCash;

    public Integer getMasterDanaId() {
        return masterDanaId;
    }

    public void setMasterDanaId(Integer masterDanaId) {
        this.masterDanaId = masterDanaId;
    }

    public String getNameBankAccount() {
        return nameBankAccount;
    }

    public void setNameBankAccount(String nameBankAccount) {
        this.nameBankAccount = nameBankAccount;
    }

    public String getNoBankAccount() {
        return noBankAccount;
    }

    public void setNoBankAccount(String noBankAccount) {
        this.noBankAccount = noBankAccount;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MasterDana{");
        sb.append("masterDanaId=").append(masterDanaId);
        sb.append(", nameBankAccount='").append(nameBankAccount).append('\'');
        sb.append(", noBankAccount='").append(noBankAccount).append('\'');
        sb.append(", nameAccount='").append(nameAccount).append('\'');
        sb.append(", totalCash=").append(totalCash);
        sb.append('}');
        return sb.toString();
    }
}