package com.sql2o.hexagonal.application.model.exception;

public class NotFoundException extends RuntimeException
{
    public NotFoundException()
    {
        /*Do Nothing*/
    }

    public NotFoundException(String id)
    {
        super(String.format("Not found %s", id));
    }
}