/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class User
{
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userHp;
    private String userAddress;
    private String userToken;
    private Date userTokenExpired;
    private String userSecurityToken;
    private Timestamp userOpenTime;
    private Timestamp userCloseTime;
    private boolean userAdmin;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserHp()
    {
        return userHp;
    }

    public void setUserHp(String userHp)
    {
        this.userHp = userHp;
    }

    public String getUserAddress()
    {
        return userAddress;
    }

    public void setUserAddress(String userAddress)
    {
        this.userAddress = userAddress;
    }

    public String getUserToken()
    {
        return userToken;
    }

    public void setUserToken(String userToken)
    {
        this.userToken = userToken;
    }

    public Date getUserTokenExpired()
    {
        return userTokenExpired;
    }

    public void setUserTokenExpired(Date userTokenExpired)
    {
        this.userTokenExpired = userTokenExpired;
    }

    public String getUserSecurityToken()
    {
        return userSecurityToken;
    }

    public void setUserSecurityToken(String userSecurityToken)
    {
        this.userSecurityToken = userSecurityToken;
    }

    public Timestamp getUserOpenTime()
    {
        return userOpenTime;
    }

    public void setUserOpenTime(Timestamp userOpenTime)
    {
        this.userOpenTime = userOpenTime;
    }

    public Timestamp getUserCloseTime()
    {
        return userCloseTime;
    }

    public void setUserCloseTime(Timestamp userCloseTime)
    {
        this.userCloseTime = userCloseTime;
    }

    public boolean isUserAdmin()
    {
        return userAdmin;
    }

    public void setUserAdmin(boolean userAdmin)
    {
        this.userAdmin = userAdmin;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userHp='" + userHp + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userToken='" + userToken + '\'' +
                ", userTokenExpired=" + userTokenExpired +
                ", userSecurityToken='" + userSecurityToken + '\'' +
                ", userOpenTime=" + userOpenTime +
                ", userCloseTime=" + userCloseTime +
                ", userAdmin=" + userAdmin +
                '}';
    }
}