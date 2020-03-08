package com.message.signature.model;

public class Message
{
    private String applicationCode;
    private String fintechCode;
    private String referenceNo;

    public String getApplicationCode()
    {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode)
    {
        this.applicationCode = applicationCode;
    }

    public String getFintechCode()
    {
        return fintechCode;
    }

    public void setFintechCode(String fintechCode)
    {
        this.fintechCode = fintechCode;
    }

    public String getReferenceNo()
    {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo)
    {
        this.referenceNo = referenceNo;
    }
}