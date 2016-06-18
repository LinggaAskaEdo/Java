package ws.zp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "province")
public class Province
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long province_id;
	
	private String province_name;

	public Province()
	{}
	
	public Province(String province_name)
	{
		this.province_name = province_name;
	}
	
	public Province(Long province_id, String province_name)
	{
		this.province_id = province_id;
		this.province_name = province_name;
	}
	
	public Long getProvince_id()
	{
		return province_id;
	}

	public void setProvince_id(Long province_id)
	{
		this.province_id = province_id;
	}

	public String getProvince_name()
	{
		return province_name;
	}

	public void setProvince_name(String province_name)
	{
		this.province_name = province_name;
	}
	
	public String toString()
	{
		return "Province [province_id=" + province_id 
			+ ", province_name=" + province_name 
			+ "]";
	}
}