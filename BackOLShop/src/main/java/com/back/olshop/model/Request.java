/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.model;

public class Request
{
    private String phone;
    private String token;
    private String message;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "Request{" +
                "phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}