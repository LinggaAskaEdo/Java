package mls.lookupservice.model;

/**
 * Created by Lingga on 23/03/17.
 */

public class ResponseMessage
{
    private int status;
    private String message;
    private Response response;

    public ResponseMessage()
    {}

    public ResponseMessage(int status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setResponse(Response response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "SuccessMessage{" + "status=" + status +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }
}