package ws.zp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users_log")
public class UserLog
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long users_log_id;
	
	private Long users_id;
	
	private String token;	

	private boolean expired;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_attempt;

	public Long getUsers_log_id()
	{
		return users_log_id;
	}

	public void setUsers_log_id(Long users_log_id)
	{
		this.users_log_id = users_log_id;
	}

	public Long getUsers_id()
	{
		return users_id;
	}

	public void setUsers_id(Long users_id)
	{
		this.users_id = users_id;
	}

	public String getToken()
	{
		return token;
	}
	
	public void setToken(String token)
	{
		this.token = token;
	}		

	public boolean isExpired()
	{
		return expired;
	}

	public void setExpired(boolean expired)
	{
		this.expired = expired;
	}

	public Date getLast_attempt()
	{
		return last_attempt;
	}

	public void setLast_attempt(Date last_attempt)
	{
		this.last_attempt = last_attempt;
	}
	
	public String toString()
	{
		return "UserLog [users_log_id=" + users_log_id 
				+ ", users_id=" + users_id 
				+ ", token=" + token 
				+ ", expired=" + expired
				+ ", last_attempt=" + last_attempt
				+ "]";
	}
}