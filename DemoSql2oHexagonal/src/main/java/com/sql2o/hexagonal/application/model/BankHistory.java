package com.sql2o.hexagonal.application.model;

import java.sql.Timestamp;

public class BankHistory
{
    private Timestamp date;
    private Long idAccountSource;
    private Long idAccountDest;
    private Double total;

    public BankHistory(Timestamp date, Long idAccountSource, Long idAccountDest, Double total)
    {
        this.date = date;
        this.idAccountSource = idAccountSource;
        this.idAccountDest = idAccountDest;
        this.total = total;
    }

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }

    public Long getIdAccountSource()
    {
        return idAccountSource;
    }

    public void setIdAccountSource(Long idAccountSource)
    {
        this.idAccountSource = idAccountSource;
    }

    public Long getIdAccountDest()
    {
        return idAccountDest;
    }

    public void setIdAccountDest(Long idAccountDest)
    {
        this.idAccountDest = idAccountDest;
    }

    public Double getTotal()
    {
        return total;
    }

    public void setTotal(Double total)
    {
        this.total = total;
    }
}
