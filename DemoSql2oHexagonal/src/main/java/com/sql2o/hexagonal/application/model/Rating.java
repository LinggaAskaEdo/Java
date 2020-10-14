package com.sql2o.hexagonal.application.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Rating
{
    @JsonAlias("Source")
    private String source;

    @JsonAlias("Value")
    private String value;

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}