package com.demo.rabbitmq.model;

import java.util.UUID;

public class Car
{
    private UUID id;

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