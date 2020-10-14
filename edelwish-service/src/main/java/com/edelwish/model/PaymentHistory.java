package com.edelwish.model;

import java.util.List;

public class PaymentHistory
{
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private List<Booking> bookingList;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Booking> getBookingList()
    {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList)
    {
        this.bookingList = bookingList;
    }
}