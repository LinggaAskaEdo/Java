/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.model;

public class Results
{
    //origin
    private String origin_name;
    private String origin_code;

    //destination
    private String destination_code;
    private String subdistrict;
    private String city;
    private String province;

    //tarif
    private String service;
    private String description;
    private int tariff;
    private String etd;

    public String getOrigin_name()
    {
        return origin_name;
    }

    public void setOrigin_name(String origin_name)
    {
        this.origin_name = origin_name;
    }

    public String getOrigin_code()
    {
        return origin_code;
    }

    public void setOrigin_code(String origin_code)
    {
        this.origin_code = origin_code;
    }

    public String getDestination_code()
    {
        return destination_code;
    }

    public void setDestination_code(String destination_code)
    {
        this.destination_code = destination_code;
    }

    public String getSubdistrict()
    {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict)
    {
        this.subdistrict = subdistrict;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getTariff()
    {
        return tariff;
    }

    public void setTariff(int tariff)
    {
        this.tariff = tariff;
    }

    public String getEtd()
    {
        return etd;
    }

    public void setEtd(String etd)
    {
        this.etd = etd;
    }

    @Override
    public String toString()
    {
        return "Results{" +
                "origin_name='" + origin_name + '\'' +
                ", origin_code='" + origin_code + '\'' +
                ", destination_code='" + destination_code + '\'' +
                ", subdistrict='" + subdistrict + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", service='" + service + '\'' +
                ", description='" + description + '\'' +
                ", tariff=" + tariff +
                ", etd='" + etd + '\'' +
                '}';
    }
}