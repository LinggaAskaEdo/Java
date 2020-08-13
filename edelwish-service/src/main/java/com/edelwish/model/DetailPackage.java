package com.edelwish.model;

import java.util.Arrays;

public class DetailPackage
{
    private Long id;
    private String name;
    private String[] details;

    public DetailPackage(Long id, String name, String[] details)
    {
        this.id = id;
        this.name = name;
        this.details = details;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String[] getDetails()
    {
        return details;
    }

    public void setDetails(String[] details)
    {
        this.details = details;
    }

    @Override
    public String toString()
    {
        return "DetailPackage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details=" + Arrays.toString(details) +
                '}';
    }
}