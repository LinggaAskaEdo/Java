package com.example.service.queues.model;

public class Response
{
    private Integer code;
    private String message;
    private String detailMessage;
    private String severity;
    private Boolean error;

    public Response()
    {}

    public Response(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, String detailMessage, String severity, boolean error)
    {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
        this.severity = severity;
        this.error = error;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public Boolean getError()
    {
        return error;
    }

    public void setError(Boolean error)
    {
        this.error = error;
    }
}