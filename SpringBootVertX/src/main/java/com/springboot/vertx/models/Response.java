package com.springboot.vertx.models;

import java.util.List;

public class Response<T>
{
    private String status;
    private String message;
    private Transactions transactions;
    private List<T> objects;

    public Response(String status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public Response(String status, String message, Transactions transactions)
    {
        this.status = status;
        this.message = message;
        this.transactions = transactions;
    }

    public Response(String status, String message, List<T> objects)
    {
        this.status = status;
        this.message = message;
        this.objects = objects;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Transactions getTransactions()
    {
        return transactions;
    }

    public void setTransactions(Transactions transactions)
    {
        this.transactions = transactions;
    }

    public List<T> getObjects()
    {
        return objects;
    }

    public void setObjects(List<T> objects)
    {
        this.objects = objects;
    }
}