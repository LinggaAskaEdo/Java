package com.sql2o.hexagonal.application.model.exception;

public class BadRequestException extends RuntimeException
{
    public BadRequestException()
    {
        /*Do Nothing*/
    }

    public BadRequestException(String message)
    {
        super(message);
    }
}