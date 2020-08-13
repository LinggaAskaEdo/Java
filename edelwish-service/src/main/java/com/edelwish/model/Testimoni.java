package com.edelwish.model;

public class Testimoni
{
    private Long id;
    private String firstname;
    private String lastname;
    private String detail;

    public Testimoni(Long id, String firstname, String lastname, String detail)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.detail = detail;
    }

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

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }
}