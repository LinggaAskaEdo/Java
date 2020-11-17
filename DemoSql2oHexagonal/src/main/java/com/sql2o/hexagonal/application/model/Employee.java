package com.sql2o.hexagonal.application.model;

import java.io.Serializable;

public class Employee implements Serializable
{
    public enum Gender
    {
        MALE, FEMALE
    }

    private String employeeId;
    private String name;
    private Gender gender;
    private int grade;

    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }
}