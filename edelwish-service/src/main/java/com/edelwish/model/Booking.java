package com.edelwish.model;

public class Booking
{
    private Long id;
    private String bookingNumber;
    private String firstname;
    private String lastname;
    private String dateTime;
    private String status;

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
}