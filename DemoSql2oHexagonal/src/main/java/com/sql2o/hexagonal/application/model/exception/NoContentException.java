package com.sql2o.hexagonal.application.model.exception;

public class NoContentException extends RuntimeException
{
    public NoContentException()
    {
        /*Do Nothing*/
    }

    public NoContentException(String message)
    {
        super(message);
    }
}