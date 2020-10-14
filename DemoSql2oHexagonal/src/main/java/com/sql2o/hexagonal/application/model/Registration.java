package com.sql2o.hexagonal.application.model;

import java.util.Date;
import java.util.UUID;

public class Registration
{
    private UUID id;
    private Date date;
    private String owner;
    private String signature;

    public Registration(UUID id, Date date, String owner, String signature)
    {
        this.id = id;
        this.date = date;
        this.owner = owner;
        this.signature = signature;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }
}