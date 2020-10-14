package com.edelwish.model;

import java.util.List;

public class Booking
{
    private Long id;
    private String bookingNumber;
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
    private String category;
    private String weddingPackage;
    private String dateTime;
    private String status;
    private List<Payment> paymentList;

    public Booking()
    {}

    public Booking(Long id, String bookingNumber, String firstname, String lastname, String dateTime, String status)
    {
        this.id = id;
        this.bookingNumber = bookingNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Booking(long id, String bookingNumber)
    {
        this.id = id;
        this.bookingNumber = bookingNumber;
    }

    public Booking(Long id, String bookingNumber, String address, String phoneNumber, String dateTime, String category, String weddingPackage)
    {
        this.id = id;
        this.bookingNumber = bookingNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateTime = dateTime;
        this.category = category;
        this.weddingPackage = weddingPackage;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getBookingNumber()
    {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber)
    {
        this.bookingNumber = bookingNumber;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getWeddingPackage()
    {
        return weddingPackage;
    }

    public void setWeddingPackage(String weddingPackage)
    {
        this.weddingPackage = weddingPackage;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public List<Payment> getPaymentList()
    {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList)
    {
        this.paymentList = paymentList;
    }
}