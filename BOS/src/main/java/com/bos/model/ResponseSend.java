/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.model;

public class ResponseSend
{
    private String success;
    private String description;
    private String result_code;

    public String getSuccess()
    {
        return success;
    }

    public void setSuccess(String success)
    {
        this.success = success;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getResult_code()
    {
        return result_code;
    }

    public void setResult_code(String result_code)
    {
        this.result_code = result_code;
    }

    @Override
    public String toString()
    {
        return "ResponseSend{" +
                "success='" + success + '\'' +
                ", description='" + description + '\'' +
                ", result_code='" + result_code + '\'' +
                '}';
    }
}