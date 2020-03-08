package com.springboot.vertx.models;

public class AircraftType
{
    private String iataMain;
    private String iataSub;

    public String getIataMain()
    {
        return iataMain;
    }

    public void setIataMain(String iataMain)
    {
        this.iataMain = iataMain;
    }

    public String getIataSub()
    {
        return iataSub;
    }

    public void setIataSub(String iataSub)
    {
        this.iataSub = iataSub;
    }
}