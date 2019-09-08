package com.callback.model;

public class Response
{
    private String status;
    private String message;
    private String desc;

    public Response()
    {}

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String status, String message, String desc)
    {
        this.status = status;
        this.message = message;
        this.desc = desc;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}