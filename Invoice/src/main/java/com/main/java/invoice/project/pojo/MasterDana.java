package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;

public class MasterDana
{
    private Integer masterDanaId;
    private String noBankAccount;
    private String nameBankAccount;
    private BigDecimal totalCash;

    public Integer getMasterDanaId()
    {
        return masterDanaId;
    }

    public void setMasterDanaId(Integer masterDanaId)
    {
        this.masterDanaId = masterDanaId;
    }

    public String getNoBankAccount()
    {
        return noBankAccount;
    }

    public void setNoBankAccount(String noBankAccount)
    {
        this.noBankAccount = noBankAccount;
    }

    public String getNameBankAccount()
    {
        return nameBankAccount;
    }

    public void setNameBankAccount(String nameBankAccount)
    {
        this.nameBankAccount = nameBankAccount;
    }

    public BigDecimal getTotalCash()
    {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash)
    {
        this.totalCash = totalCash;
    }

    @Override
    public String toString()
    {
        return "MasterDana{" +
                "masterDanaId=" + masterDanaId +
                ", noBankAccount='" + noBankAccount + '\'' +
                ", nameBankAccount='" + nameBankAccount + '\'' +
                ", totalCash=" + totalCash +
                '}';
    }
}