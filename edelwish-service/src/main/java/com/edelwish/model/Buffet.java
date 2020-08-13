package com.edelwish.model;

import java.util.List;

public class Buffet
{
    private Long id;
    private String name;
    private String detailBuffet;
    private List<DetailBuffet> detailBuffets;

    public Buffet(Long id, String name, String detailBuffet)
    {
        this.id = id;
        this.name = name;
        this.detailBuffet = detailBuffet;
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

    public String getDetailBuffet()
    {
        return detailBuffet;
    }

    public void setDetailBuffet(String detailBuffet)
    {
        this.detailBuffet = detailBuffet;
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