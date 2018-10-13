package id.co.homecredit.simulator.model;

public class Response
{
    private String status;
    private String message;
    private String publicKey;
    private String DOB;

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

    public String getPublicKey()
    {
        return publicKey;
    }

    public void setPublicKey(String publicKey)
    {
        this.publicKey = publicKey;
    }

    public String getDOB()
    {
        return DOB;
    }

    public void setDOB(String DOB)
    {
        this.DOB = DOB;
    }

    @Override
    public String toString()
    {
        return "Response{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", DOB='" + DOB + '\'' +
                '}';
    }
}