package com.main.sample.token.model;

public class Response
{
    private String status;
    private String message;
    private AuthenticatedToken token;

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

    public AuthenticatedToken getToken()
    {
        return token;
    }

    public void setToken(AuthenticatedToken token)
    {
        this.token = token;
    }

    @Override
    public String toString()
    {
        return "Response{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", token=" + token +
                '}';
    }
}