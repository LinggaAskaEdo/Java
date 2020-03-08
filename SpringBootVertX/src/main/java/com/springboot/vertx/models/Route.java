package com.springboot.vertx.models;

public class Route
{
    private String[] destinations;
    private String eu;
    private boolean visa;

    public String[] getDestinations()
    {
        return destinations;
    }

    public void setDestinations(String[] destinations)
    {
        this.destinations = destinations;
    }

    public String getEu()
    {
        return eu;
    }

    public void setEu(String eu)
    {
        this.eu = eu;
    }

    public boolean isVisa()
    {
        return visa;
    }

    public void setVisa(boolean visa)
    {
        this.visa = visa;
    }
}