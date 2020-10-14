package com.sql2o.hexagonal.application.model;

import java.util.UUID;

public class Car
{
    private UUID id;

    public Car()
    {}

    public Car(UUID id)
    {
        this.id = id;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }
}