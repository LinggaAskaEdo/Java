/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.model;

import java.util.List;

public class Sicepat
{
    private Status status;
    private List<Results> results;

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public List<Results> getResults()
    {
        return results;
    }

    public void setResults(List<Results> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "Sicepat{" +
                "status=" + status +
                ", results=" + results +
                '}';
    }
}