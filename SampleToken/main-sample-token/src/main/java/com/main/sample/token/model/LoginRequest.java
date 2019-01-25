package com.main.sample.token.model;

public class LoginRequest
{
    private String userId;
    private String pin;
    private String loginType;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPin()
    {
        return pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    public String getLoginType()
    {
        return loginType;
    }

    public void setLoginType(String loginType)
    {
        this.loginType = loginType;
    }

    @Override
    public String toString()
    {
        return "LoginRequest{" +
                "userId='" + userId + '\'' +
                ", pin='" + pin + '\'' +
                ", loginType='" + loginType + '\'' +
                '}';
    }
}