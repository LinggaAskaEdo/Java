package com.sql2o.hexagonal.application.model.exception;

public class InternalServerErrorException extends RuntimeException
{
    public InternalServerErrorException()
    {
        /*Do Nothing*/
    }

    public InternalServerErrorException(String message)
    {
        super(message);
    }
}
