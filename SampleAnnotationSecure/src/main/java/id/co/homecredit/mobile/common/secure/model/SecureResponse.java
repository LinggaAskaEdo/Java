package id.co.homecredit.mobile.common.secure.model;

public class SecureResponse
{
    private String status;
    private String message;

    public SecureResponse(String status, String message)
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

    @Override
    public String toString()
    {
        return "Response{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}