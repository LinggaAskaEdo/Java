package com.edelwish.exception;

public class BadRequestException extends RuntimeException
{
    public BadRequestException()
    {
        // Do Nothing
    }

    public BadRequestException(String message)
    {
        super(message);
    }
}