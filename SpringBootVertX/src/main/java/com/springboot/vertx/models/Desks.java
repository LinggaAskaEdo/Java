package com.springboot.vertx.models;

public class Desks
{
    private CheckinClass checkinClass;
    private String position;

    public CheckinClass getCheckinClass ()
    {
        return checkinClass;
    }

    public void setCheckinClass (CheckinClass checkinClass)
    {
        this.checkinClass = checkinClass;
    }

    public String getPosition ()
    {
        return position;
    }

    public void setPosition (String position)
    {
        this.position = position;
    }
}