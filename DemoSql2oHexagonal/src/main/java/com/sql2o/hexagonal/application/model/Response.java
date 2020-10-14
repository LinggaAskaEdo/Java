package com.sql2o.hexagonal.application.model;

import java.util.List;

public class Response
{
    private Integer code;
    private String message;
    private String detailMessage;
    private String severity;
    private Boolean error;

    private Student student;
    private List<Student> students;
    private BankAccount bankAccount;
    private List<BankAccount> bankAccounts;
    private MovieInfo movieInfo;

    public Response()
    {}

    public Response(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public Response(Integer code, String message, String detailMessage, String severity, Boolean error)
    {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
        this.severity = severity;
        this.error = error;
    }

    public Response(Student student)
    {
        this.student = student;
    }

    public Response(BankAccount bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public Response(MovieInfo movieInfo)
    {
        this.movieInfo = movieInfo;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public Boolean getError()
    {
        return error;
    }

    public void setError(Boolean error)
    {
        this.error = error;
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public BankAccount getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public List<BankAccount> getBankAccounts()
    {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts)
    {
        this.bankAccounts = bankAccounts;
    }

    public MovieInfo getMovieInfo()
    {
        return movieInfo;
    }

    public void setMovieInfo(MovieInfo movieInfo)
    {
        this.movieInfo = movieInfo;
    }
}