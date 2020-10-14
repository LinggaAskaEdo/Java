package com.hexagonal.sample.application.domain;

import java.math.BigDecimal;

public class BankAccount
{
    private Long id;
    private BigDecimal balance;

    public BankAccount(Long id, BigDecimal balance)
    {
        this.id = id;
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

    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public boolean withdraw(BigDecimal amount)
    {
        if (balance.compareTo(amount) < 0)
        {
            return false;
        }

        balance = balance.subtract(amount);

        return true;
    }

    public void deposit(BigDecimal amount)
    {
        balance = balance.add(amount);
    }
}