/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.constant;

public enum ApplicationStatus
{
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    CRITICAL_UPDATE("CRITICAL UPDATE"),
    ACCOUNT_EXIST("ACCOUNT EXIST"),
    NOT_FOUND("NOT FOUND"),
    MAINTENANCE("MAINTENANCE");

    private final String applicationStatus;

    ApplicationStatus(String value)
    {
        this.applicationStatus = value;
    }

    @Override
    public String toString()
    {
        return this.applicationStatus;
    }
}