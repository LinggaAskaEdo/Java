package com.edelwish.model;

import java.util.List;

public class WeddingPackage
{
    private Long id;
    private String name;
    private String price;
    private String totalBuffet;
    private Long buffetId;
    private String buffetName;
    private List<DetailBuffet> detailBuffets;
    private String detailPackage;
    private List<DetailPackage> detailPackages;
    private String[] bonus;

    public WeddingPackage()
    {}

    public WeddingPackage(Long id, String name, String price, String detailPackage)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detailPackage = detailPackage;
    }

    public WeddingPackage(Long id, String name, String price, String detailPackage, String[] bonus)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detailPackage = detailPackage;
        this.bonus = bonus;
    }

    public WeddingPackage(Long id, String name, String price, String[] bonus)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.bonus = bonus;
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

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getTotalBuffet()
    {
        return totalBuffet;
    }

    public void setTotalBuffet(String totalBuffet)
    {
        this.totalBuffet = totalBuffet;
    }

    public Long getBuffetId()
    {
        return buffetId;
    }

    public void setBuffetId(Long buffetId)
    {
        this.buffetId = buffetId;
    }

    public String getBuffetName()
    {
        return buffetName;
    }

    public void setBuffetName(String buffetName)
    {
        this.buffetName = buffetName;
    }

    public List<DetailBuffet> getDetailBuffets()
    {
        return detailBuffets;
    }

    public void setDetailBuffets(List<DetailBuffet> detailBuffets)
    {
        this.detailBuffets = detailBuffets;
    }

    public String getDetailPackage()
    {
        return detailPackage;
    }

    public void setDetailPackage(String detailPackage)
    {
        this.detailPackage = detailPackage;
    }

    public List<DetailPackage> getDetailPackages()
    {
        return detailPackages;
    }

    public void setDetailPackages(List<DetailPackage> detailPackages)
    {
        this.detailPackages = detailPackages;
    }

    public String[] getBonus()
    {
        return bonus;
    }

    public void setBonus(String[] bonus)
    {
        this.bonus = bonus;
    }
}