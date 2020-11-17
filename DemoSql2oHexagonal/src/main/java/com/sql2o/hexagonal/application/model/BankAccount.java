package com.sql2o.hexagonal.application.model;

import java.io.Serializable;

public class BankAccount implements Serializable
{
    private Long id;
    private String fullName;
    private Double balance;

    public BankAccount()
    {}

    public BankAccount(Long id, String fullName, double balance)
    {
        this.id = id;
        this.fullName = fullName;
        this.balance = balance;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public Double getBalance()
    {
        return balance;
    }

    public void setBalance(Double balance)
    {
        this.balance = balance;
    }
}