package ws.zp.model;

public class ReturnMessage
{
	private String status;
	private String message;
	
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
	
	public String toString()
	{
		return "ErrorMessage [status=" + status + ", message=" + message + "]";
	}	
}