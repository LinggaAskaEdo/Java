package ws.zp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long users_id;
	
	private String username;
	
	private String password;
	
	private String full_name;
	
	private String phone_number;
	
	private String company_name;
	
	private String company_address;
	
	private String company_phone_number;
	
	private String billing_address;
	
	private String shipping_address;

	public User()
	{}	

	public User(Long users_id)
	{
		this.users_id = users_id;
	}
	
	public User(String username)
	{
		this.username = username;
	}
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public User(long users_id, String username, String password, String full_name, String phone_number, String company_name, String company_address, String company_phone_number, 
			String billing_address, String shipping_address)
	{
		this.users_id = users_id;
		this.username = username;
		this.password = password;
		this.full_name = full_name;
		this.phone_number = phone_number;
		this.company_name = company_name;
		this.company_address = company_address;
		this.company_phone_number = company_phone_number;
		this.billing_address = billing_address;
		this.shipping_address = shipping_address;
	}
	
	public Long getUsers_id()
	{
		return users_id;
	}

	public void setUsers_id(Long users_id)
	{
		this.users_id = users_id;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}	

	public String getFull_name()
	{
		return full_name;
	}

	public void setFull_name(String full_name)
	{
		this.full_name = full_name;
	}

	public String getPhone_number()
	{
		return phone_number;
	}

	public void setPhone_number(String phone_number)
	{
		this.phone_number = phone_number;
	}	
	
	public String getCompany_name()
	{
		return company_name;
	}

	public void setCompany_name(String company_name)
	{
		this.company_name = company_name;
	}

	public String getCompany_address()
	{
		return company_address;
	}

	public void setCompany_address(String company_address)
	{
		this.company_address = company_address;
	}

	public String getCompany_phone_number()
	{
		return company_phone_number;
	}

	public void setCompany_phone_number(String company_phone_number)
	{
		this.company_phone_number = company_phone_number;
	}

	public String getBilling_address()
	{
		return billing_address;
	}

	public void setBilling_address(String billing_address)
	{
		this.billing_address = billing_address;
	}

	public String getShipping_address()
	{
		return shipping_address;
	}

	public void setShipping_address(String shipping_address)
	{
		this.shipping_address = shipping_address;
	}

	public String toString()
	{
		return "User [users_id=" + users_id 
				+ ", username=" + username 
				+ ", password=" + password
				+ ", full_name=" + full_name
				+ ", phone_number=" + phone_number
				+ ", company_name=" + company_name
				+ ", company_address=" + company_address
				+ ", company_phone_number=" + company_phone_number
				+ ", billing_address=" + billing_address
				+ ", shipping_address=" + shipping_address
				+ "]";
	}
}