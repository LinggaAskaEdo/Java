/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.model;

public class Response
{
    private String status;
    private String message;
    private Sicepat sicepat;

    public Response()
    {}

    public Response(String message)
    {
        this.message = message;
    }

    public Response(String status, String message)
    {
        this.status = status;
        this.message = message;
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

    public Sicepat getSicepat()
    {
        return sicepat;
    }

    public void setSicepat(Sicepat sicepat)
    {
        this.sicepat = sicepat;
    }

    @Override
    public String toString()
    {
        return "Response{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", sicepat=" + sicepat +
                '}';
    }
}