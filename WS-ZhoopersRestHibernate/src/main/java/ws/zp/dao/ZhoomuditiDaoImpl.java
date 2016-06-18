package ws.zp.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ws.zp.model.Category;
import ws.zp.model.Comment;
import ws.zp.model.ContactUs;
import ws.zp.model.Item;
import ws.zp.model.User;
import ws.zp.model.UserLog;

@Repository
@Transactional(value="transactionManagerZhmdt", readOnly = true)
public class ZhoomuditiDaoImpl implements ZhoomuditiDao
{
	@Autowired
	@Qualifier("sessionFactoryZhmdt")
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser()
	{
		return getCurrentSession().createQuery("from User").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> checkExistUser(String username)
	{
		Query query = getCurrentSession().createQuery("from User where username = :username");
		query.setParameter("username", username);
		
		List<User> listUser = query.list();
		
		return listUser;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> checkLogin(String username, String password)
	{
		Query query = getCurrentSession().createQuery("from User where username = :username and password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		List<User> listUser = query.list();
		
		return listUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserLog> checkToken(Long id)
	{
		Query query = getCurrentSession().createQuery("from UserLog where users_id = :users_id order by users_log_id desc");
		query.setParameter("users_id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		List<UserLog> listUserLog = query.list();
		
		return listUserLog;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserLog> findSession(Long id, String token)
	{
		Query query = getCurrentSession().createQuery("from UserLog where users_id = :users_id and token = :token and expired = :expired order by users_log_id desc");
		query.setParameter("users_id", id);
		query.setParameter("token", token);
		query.setParameter("expired", false);
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		List<UserLog> listUserLog = query.list();
		
		return listUserLog;
	}
	
	@Override
	public void addUserLog(UserLog userLog)
	{
		getCurrentSession().save(userLog);		
	}

	@Override
	public void userLogout(UserLog userLog)
	{
		getCurrentSession().update(userLog);
	}

	@Override
	public void saveUser(User user)
	{
		getCurrentSession().save(user);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> loadCategory()
	{
		Query query = getCurrentSession().createQuery("from Category");
		
		List<Category> listCategory = query.list();
		
		return listCategory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> loadItem()
	{
		Query query = getCurrentSession().createQuery("from Item");
		
		List<Item> listItem = query.list();
		
		return listItem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> loadItemById(Long id)
	{
		Query query = getCurrentSession().createQuery("from Item where item_id = :item_id");
		query.setParameter("item_id", id);
		
		List<Item> listItem = query.list();
		
		return listItem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> loadItemByCategoryId(Long id, boolean enableAsc)
	{
		String sortBy = enableAsc ? "order by item_name asc" : "order by item_name desc";
		
		Query query = getCurrentSession().createQuery("from Item where category_id = :category_id " + sortBy);
		query.setParameter("category_id", id);
				
		List<Item> listItem = query.list();
		
		return listItem;
	}

	@Override
	public void saveComment(Comment comment)
	{
		getCurrentSession().save(comment);		
	}

	@Override
	public void reportComment(Comment comment)
	{
		getCurrentSession().update(comment);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> loadCommentByItemId(Long id)
	{
		Query query = getCurrentSession().createQuery("from Comment where item_id = :item_id and status_report = false");
		query.setParameter("item_id", id);
		
		List<Comment> listComment = query.list();
		
		return listComment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> loadCommentByCommentId(Long id)
	{
		Query query = getCurrentSession().createQuery("from Comment where comment_id = :comment_id");
		query.setParameter("comment_id", id);
		
		List<Comment> listComment = query.list();
		
		return listComment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> searchItem(Long id, String criteria)
	{
		Query query;
		
		if (id != 0)
		{
			query = getCurrentSession().createQuery("from Item where category_id = :category_id and item_name like :item_name");
			query.setParameter("category_id", id);			
		}
		else
		{
			query = getCurrentSession().createQuery("from Item where item_name like :item_name");		
		}
		
		query.setParameter("item_name", "%" + criteria + "%");
		
		List<Item> listItem = query.list();
		
		return listItem;
	}

	@Override
	public void saveContactUs(ContactUs contactUs)
	{
		getCurrentSession().save(contactUs);	
	}	
}