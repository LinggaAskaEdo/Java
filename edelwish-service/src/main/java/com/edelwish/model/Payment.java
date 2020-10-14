package com.edelwish.model;

import java.sql.Date;

public class Payment
{
    private Long id;
    private Long idBooking;
    private Long idPaymentType;
    private String paymentType;
    private Date paymentDate;
    private Long paymentTotal;
    private String paymentReceipt;

    public Payment(Long id, Long idBooking, Long idPaymentType, Date paymentDate, String paymentReceipt)
    {
        this.id = id;
        this.idBooking = idBooking;
        this.idPaymentType = idPaymentType;
        this.paymentDate = paymentDate;
        this.paymentReceipt = paymentReceipt;
    }

    public Payment(Long id, String paymentType, Date paymentDate, Long paymentTotal, String paymentReceipt)
    {
        this.id = id;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentTotal = paymentTotal;
        this.paymentReceipt = paymentReceipt;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getIdBooking()
    {
        return idBooking;
    }

    public void setIdBooking(Long idBooking)
    {
        this.idBooking = idBooking;
    }

    public Long getIdPaymentType()
    {
        return idPaymentType;
    }

    public void setIdPaymentType(Long idPaymentType)
    {
        this.idPaymentType = idPaymentType;
    }

    public String getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(String paymentType)
    {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public Long getPaymentTotal()
    {
        return paymentTotal;
    }

    public void setPaymentTotal(Long paymentTotal)
    {
        this.paymentTotal = paymentTotal;
    }

    public String getPaymentReceipt()
    {
        return paymentReceipt;
    }

    public void setPaymentReceipt(String paymentReceipt)
    {
        this.paymentReceipt = paymentReceipt;
    }
}
