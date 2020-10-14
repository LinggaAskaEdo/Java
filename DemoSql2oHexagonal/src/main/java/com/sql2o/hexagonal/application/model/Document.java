package com.sql2o.hexagonal.application.model;

public class Document
{
    private String cuid;
    private String contractNumber;

    public String getCuid()
    {
        return cuid;
    }

    public void setCuid(String cuid)
    {
        this.cuid = cuid;
    }

    public String getContractNumber()
    {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber)
    {
        this.contractNumber = contractNumber;
    }
}