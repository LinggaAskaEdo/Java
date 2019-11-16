package springboot.timeout.model;

public class Response
{
    private String status;
    private String message;

    public Response(String status, String message)
    {
        this.status = status;
        this.message = message;
    }
}
