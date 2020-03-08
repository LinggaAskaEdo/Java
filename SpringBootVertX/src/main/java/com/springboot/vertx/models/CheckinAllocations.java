package com.springboot.vertx.models;

public class CheckinAllocations
{
    private CheckinAllocations[] checkinAllocations;
    private String startTime;
    private String endTime;
    private Rows rows;

    public String getStartTime ()
    {
        return startTime;
    }

    public void setStartTime (String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime ()
    {
        return endTime;
    }

    public void setEndTime (String endTime)
    {
        this.endTime = endTime;
    }

    public Rows getRows ()
    {
        return rows;
    }

    public void setRows (Rows rows)
    {
        this.rows = rows;
    }
}