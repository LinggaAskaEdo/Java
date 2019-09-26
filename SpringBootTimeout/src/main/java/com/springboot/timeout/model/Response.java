package com.springboot.timeout.model;

public class Response 
{
	private String status;
	private String message;
	
	public Response() 
	{}
	
	public Response(String status, String message)
	{
		this.status = status;
		this.message = message;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "Response [status=" + status + ", message=" + message + "]";
	}
}