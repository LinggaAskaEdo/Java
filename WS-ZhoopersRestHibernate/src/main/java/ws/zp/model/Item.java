package ws.zp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long item_id;
	private Long category_id;
	private String item_name;
	private String item_description;
	private String item_detail;
	private String item_image_path_1;
	private String item_image_path_2;
	private String item_image_path_3;
	private String item_image_path_4;
	private String item_image_path_5;
		
	public Item()
	{}
	
	public Item(Long id, boolean status)
	{
		if (status)
		{
			this.item_id = id;
		}
		else
		{
			this.category_id = id;
		}		
	}
	
	public Item(Long item_id, Long category_id, String item_name, String item_description, String item_image_path_1)
	{
		this.item_id = item_id;
		this.category_id = category_id;
		this.item_name = item_name;
		this.item_description = item_description;
		this.item_image_path_1 = item_image_path_1;
	}
	
	public Item(Long item_id, Long category_id, String item_name, String item_description, String item_detail, String item_image_path_1, String item_image_path_2, 
			String item_image_path_3, String item_image_path_4, String item_image_path_5)
	{
		this.item_id = item_id;
		this.category_id = category_id;
		this.item_name = item_name;
		this.item_description = item_description;
		this.item_detail = item_detail;
		this.item_image_path_1 = item_image_path_1;
		this.item_image_path_2 = item_image_path_2;
		this.item_image_path_3 = item_image_path_3;
		this.item_image_path_4 = item_image_path_4;
		this.item_image_path_5 = item_image_path_5;
	}
	
	public Long getItem_id()
	{
		return item_id;
	}

	public void setItem_id(Long item_id)
	{
		this.item_id = item_id;
	}

	public Long getCategory_id()
	{
		return category_id;
	}

	public void setCategory_id(Long category_id)
	{
		this.category_id = category_id;
	}

	public String getItem_name()
	{
		return item_name;
	}

	public void setItem_name(String item_name)
	{
		this.item_name = item_name;
	}

	public String getItem_description()
	{
		return item_description;
	}

	public void setItem_description(String item_description)
	{
		this.item_description = item_description;
	}

	public String getItem_detail()
	{
		return item_detail;
	}

	public void setItem_detail(String item_detail)
	{
		this.item_detail = item_detail;
	}

	public String getItem_image_path_1()
	{
		return item_image_path_1;
	}

	public void setItem_image_path_1(String item_image_path_1)
	{
		this.item_image_path_1 = item_image_path_1;
	}

	public String getItem_image_path_2()
	{
		return item_image_path_2;
	}

	public void setItem_image_path_2(String item_image_path_2)
	{
		this.item_image_path_2 = item_image_path_2;
	}

	public String getItem_image_path_3()
	{
		return item_image_path_3;
	}

	public void setItem_image_path_3(String item_image_path_3)
	{
		this.item_image_path_3 = item_image_path_3;
	}

	public String getItem_image_path_4()
	{
		return item_image_path_4;
	}

	public void setItem_image_path_4(String item_image_path_4)
	{
		this.item_image_path_4 = item_image_path_4;
	}

	public String getItem_image_path_5()
	{
		return item_image_path_5;
	}

	public void setItem_image_path_5(String item_image_path_5)
	{
		this.item_image_path_5 = item_image_path_5;
	}
	
	public String toString()
	{
		return "Item [item_id=" + item_id 
				+ ", category_id=" + category_id 
				+ ", item_name=" + item_name
				+ ", item_description=" + item_description
				+ ", item_detail=" + item_detail
				+ ", item_image_path_1=" + item_image_path_1
				+ ", item_image_path_2=" + item_image_path_2
				+ ", item_image_path_3=" + item_image_path_3
				+ ", item_image_path_4=" + item_image_path_4
				+ ", item_image_path_5=" + item_image_path_5
				+ "]";
	}
}