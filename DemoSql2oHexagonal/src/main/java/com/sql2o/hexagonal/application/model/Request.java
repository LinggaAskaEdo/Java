package com.sql2o.hexagonal.application.model;

public class Request extends Student
{
    private String fullName;
    private Long sourceId;
    private Long destId;
    private Double amount;

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public Long getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(Long sourceId)
    {
        this.sourceId = sourceId;
    }

    public Long getDestId()
    {
        return destId;
    }

    public void setDestId(Long destId)
    {
        this.destId = destId;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }
}