package ws.zp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_id;
	private String category_name;
	private int parent_id;
	
	public Category()
	{}

	public Long getCategory_id()
	{
		return category_id;
	}

	public void setCategory_id(Long category_id)
	{
		this.category_id = category_id;
	}

	public String getCategory_name()
	{
		return category_name;
	}

	public void setCategory_name(String category_name)
	{
		this.category_name = category_name;
	}

	public int getParent_id()
	{
		return parent_id;
	}

	public void setParent_id(int parent_id)
	{
		this.parent_id = parent_id;
	}
	
	public String toString()
	{
		return "Category [category_id=" + category_id 
				+ ", category_name=" + category_name 
				+ ", parent_id=" + parent_id
				+ "]";
	}
}