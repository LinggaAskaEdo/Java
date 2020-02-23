package org.otis.reactive.models;

public class Rows
{
    private Desks desks;
    private String position;

    public Desks getDesks ()
    {
        return desks;
    }

    public void setDesks (Desks desks)
    {
        this.desks = desks;
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