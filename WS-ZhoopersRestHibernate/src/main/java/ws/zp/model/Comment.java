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
@Table(name = "comment")
public class Comment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comment_id;
	
	private Long users_id;
	
	private Long item_id;	
	
	private String comment_text;
	
	private Long comment_parent_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date comment_date;
	
	private boolean status_report;
	
	public Comment()
	{}
	
	public Comment(Long comment_id)
	{
		this.comment_id = comment_id;
	}
	
	public Comment(Long comment_id, boolean status_report)
	{
		this.comment_id = comment_id;
		this.status_report = status_report;
	}
	
	public Comment(Long item_id, String comment_text, Long comment_parent_id)
	{
		this.item_id = item_id;
		this.comment_text = comment_text;
		this.comment_parent_id = comment_parent_id;
	}
	
	public Comment(Long item_id, String comment_text, Long comment_parent_id, Date comment_date, boolean status_report)
	{
		this.item_id = item_id;
		this.comment_text = comment_text;
		this.comment_parent_id = comment_parent_id;
		this.comment_date = comment_date;
		this.status_report = status_report;
	}
	
	public Comment(Long users_id, Long item_id, String comment_text, Long comment_parent_id, Date comment_date, boolean status_report)
	{
		this.users_id = users_id;
		this.item_id = item_id;		
		this.comment_text = comment_text;
		this.comment_parent_id = comment_parent_id;
		this.comment_date = comment_date;
		this.status_report = status_report;
	}
	
	public Long getComment_id()
	{
		return comment_id;
	}	

	public void setComment_id(Long comment_id)
	{
		this.comment_id = comment_id;
	}
	
	public Long getUsers_id()
	{
		return users_id;
	}

	public void setUsers_id(Long users_id)
	{
		this.users_id = users_id;
	}
	
	public Long getItem_id()
	{
		return item_id;
	}
	
	public void setItem_id(Long item_id)
	{
		this.item_id = item_id;
	}
	
	public String getComment_text()
	{
		return comment_text;
	}
	
	public void setComment_text(String comment_text)
	{
		this.comment_text = comment_text;
	}
	
	public Long getComment_parent_id()
	{
		return comment_parent_id;
	}
	
	public void setComment_parent_id(Long comment_parent_id)
	{
		this.comment_parent_id = comment_parent_id;
	}
	
	public Date getComment_date()
	{
		return comment_date;
	}

	public void setComment_date(Date comment_date)
	{
		this.comment_date = comment_date;
	}
	
	public boolean isStatus_report()
	{
		return status_report;
	}

	public void setStatus_report(boolean status_report)
	{
		this.status_report = status_report;
	}
	
	public String toString()
	{
		return "Comment [comment_id=" + comment_id 
				+ ", users_id" + users_id
				+ ", item_id=" + item_id				
				+ ", comment_text=" + comment_text
				+ ", comment_parent_id=" + comment_parent_id
				+ ", comment_date=" + comment_date
				+ ", status_report=" + status_report
				+ "]";
	}
}