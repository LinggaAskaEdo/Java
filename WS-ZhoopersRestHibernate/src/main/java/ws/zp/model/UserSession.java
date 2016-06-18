package ws.zp.model;

public class UserSession
{
	private String username;
	private String token;
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public String toString()
	{
		return "UserSession [username=" + username 
				+ ", token=" + token 
				+ "]";
	}
}