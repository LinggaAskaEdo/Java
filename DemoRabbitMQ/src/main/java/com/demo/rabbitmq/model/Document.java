package com.demo.rabbitmq.model;

public class Document
{
    private String cuid;
    private String contractNumber;

    public Document(String cuid, String contractNumber)
    {
        this.cuid = cuid;
        this.contractNumber = contractNumber;
    }

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