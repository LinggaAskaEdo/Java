/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.model;

public class Status
{
    private int code;
    private String description;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Status{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}