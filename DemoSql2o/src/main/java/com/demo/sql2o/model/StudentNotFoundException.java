package com.demo.sql2o.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException
{
    private String id;

    public StudentNotFoundException(String id)
    {
        super(String.format("not found %s", id));
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }
}