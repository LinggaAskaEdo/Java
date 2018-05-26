/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.model;

public class ResponseGet
{
    private String id;
    private String number;
    private String from;
    private String to;
    private String type;
    private String text;
    private String creation_date;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getCreation_date()
    {
        return creation_date;
    }

    public void setCreation_date(String creation_date)
    {
        this.creation_date = creation_date;
    }

    @Override
    public String toString()
    {
        return "ResponseGet{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", creation_date='" + creation_date + '\'' +
                '}';
    }
}