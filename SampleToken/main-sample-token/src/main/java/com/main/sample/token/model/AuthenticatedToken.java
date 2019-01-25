package com.main.sample.token.model;

import java.util.Date;

public class AuthenticatedToken
{
    private String accessToken;
    private String refreshToken;
    private long expired;
    private String username;
    private Date tokenCreated;
    private Date tokenExpired;

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken, LoginRequest request)
    {
        this.refreshToken = refreshToken;
    }

    public long getExpired()
    {
        return expired;
    }

    public void setExpired(long expired)
    {
        this.expired = expired;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Date getTokenCreated()
    {
        return tokenCreated;
    }

    public void setTokenCreated(Date tokenCreated)
    {
        this.tokenCreated = tokenCreated;
    }

    public Date getTokenExpired()
    {
        return tokenExpired;
    }

    public void setTokenExpired(Date tokenExpired)
    {
        this.tokenExpired = tokenExpired;
    }

    @Override
    public String toString()
    {
        return "AuthenticatedToken{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expired=" + expired +
                ", username='" + username + '\'' +
                ", tokenCreated=" + tokenCreated +
                ", tokenExpired=" + tokenExpired +
                '}';
    }
}