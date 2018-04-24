/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.model;

import java.sql.Time;

public class User
{
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userToken;
    private String userHp;
    private Time userOpenTime;
    private Time userCloseTime;
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

    public String getUserToken()
    {
        return userToken;
    }

    public void setUserToken(String userToken)
    {
        this.userToken = userToken;
    }

    public String getUserHp()
    {
        return userHp;
    }

    public void setUserHp(String userHp)
    {
        this.userHp = userHp;
    }

    public Time getUserOpenTime()
    {
        return userOpenTime;
    }

    public void setUserOpenTime(Time userOpenTime)
    {
        this.userOpenTime = userOpenTime;
    }

    public Time getUserCloseTime() {
        return userCloseTime;
    }

    public void setUserCloseTime(Time userCloseTime) {
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
                ", userToken='" + userToken + '\'' +
                ", userHp='" + userHp + '\'' +
                ", userOpenTime=" + userOpenTime +
                ", userCloseTime=" + userCloseTime +
                ", userAdmin=" + userAdmin +
                '}';
    }
}