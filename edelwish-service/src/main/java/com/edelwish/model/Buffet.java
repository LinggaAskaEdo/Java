package com.edelwish.model;

import java.util.List;

public class Buffet
{
    private Long id;
    private String name;
    private List<DetailBuffet> detailBuffets;

    public Buffet(Long id, String name)
    {
        this.id = id;
        this.name = name;
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

    public List<DetailBuffet> getDetailBuffets()
    {
        return detailBuffets;
    }

    public void setDetailBuffets(List<DetailBuffet> detailBuffets)
    {
        this.detailBuffets = detailBuffets;
    }
}