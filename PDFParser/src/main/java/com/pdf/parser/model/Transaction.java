package com.pdf.parser.model;

public class Transaction
{
    private String noContent;
    private String bodyContent;

    public String getNoContent()
    {
        return noContent;
    }

    public void setNoContent(String noContent)
    {
        this.noContent = noContent;
    }

    public String getBodyContent()
    {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent)
    {
        this.bodyContent = bodyContent;
    }

    @Override
    public String toString()
    {
        return "Transaction{" +
                "noContent='" + noContent + '\'' +
                ", bodyContent='" + bodyContent + '\'' +
                '}';
    }
}