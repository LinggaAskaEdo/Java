package ws.zp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact_us")
public class ContactUs
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contact_us_id;
	
	private String username;
	
	private String email;
	
	private String subject;
	
	private String description;
	
	public ContactUs()
	{}
	
	public ContactUs(String username, String email, String subject, String description)
	{
		this.username = username;
		this.email = email;
		this.subject = subject;
		this.description = description;
	}

	public Long getContact_us_id()
	{
		return contact_us_id;
	}

	public void setContact_us_id(Long contact_us_id)
	{
		this.contact_us_id = contact_us_id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String toString()
	{
		return "ContactUs [contact_us_id=" + contact_us_id 
				+ ", username" + username
				+ ", email=" + email				
				+ ", subject=" + subject
				+ ", description=" + description
				+ "]";
	}
}