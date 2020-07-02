package org.o7planning.sbjdbctrans.model;

public class Response
{
    private String status;
    private String message;
    private MovieInfo movieInfo;
    private BankAccountInfo accountInfo;

    public Response()
    {}

    public Response(String status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public MovieInfo getMovieInfo()
    {
        return movieInfo;
    }

    public void setMovieInfo(MovieInfo movieInfo)
    {
        this.movieInfo = movieInfo;
    }

    public BankAccountInfo getAccountInfo()
    {
        return accountInfo;
    }

    public void setAccountInfo(BankAccountInfo accountInfo)
    {
        this.accountInfo = accountInfo;
    }
}
