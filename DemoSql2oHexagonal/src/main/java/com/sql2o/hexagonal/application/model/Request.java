package com.sql2o.hexagonal.application.model;

public class Request
{
    private String fullName;
    private Long sourceId;
    private Long destId;
    private Double amount;

    //Student
    private Long studentId;
    private String nim;
    private String studentName;
    private String passportNumber;

    //Employee
    private String employeeId;
    private String employeeName;
    private Employee.Gender gender;
    private int grade;

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public Long getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(Long sourceId)
    {
        this.sourceId = sourceId;
    }

    public Long getDestId()
    {
        return destId;
    }

    public void setDestId(Long destId)
    {
        this.destId = destId;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public Long getStudentId()
    {
        return studentId;
    }

    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    public String getNim()
    {
        return nim;
    }

    public void setNim(String nim)
    {
        this.nim = nim;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getPassportNumber()
    {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber)
    {
        this.passportNumber = passportNumber;
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }

    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public Employee.Gender getGender()
    {
        return gender;
    }

    public void setGender(Employee.Gender gender)
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