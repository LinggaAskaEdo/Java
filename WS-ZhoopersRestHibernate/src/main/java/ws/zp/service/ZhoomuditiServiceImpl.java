package ws.zp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.zp.dao.ZhoomuditiDao;
import ws.zp.model.Category;
import ws.zp.model.Comment;
import ws.zp.model.ContactUs;
import ws.zp.model.Item;
import ws.zp.model.User;
import ws.zp.model.UserLog;

@Service
@Transactional
public class ZhoomuditiServiceImpl implements ZhoomuditiService
{
	@Autowired
	private ZhoomuditiDao zhoomuditiDao;
	
	@Override
	public List<User> getUser()
	{
		return zhoomuditiDao.getUser();
	}	
	
	@Override
	public List<User> checkExistUser(String username)
	{
		return zhoomuditiDao.checkExistUser(username);
	}
	
	@Override
	public List<User> checkLogin(String username, String password)
	{
		return zhoomuditiDao.checkLogin(username, password);
	}

	@Override
	public List<UserLog> checkToken(Long id)
	{
		return zhoomuditiDao.checkToken(id);
	}

	@Override
	public List<UserLog> findSession(Long id, String token)
	{
		return zhoomuditiDao.findSession(id, token);
	}
	
	@Override
	public void addUserLog(UserLog userLog)
	{
		zhoomuditiDao.addUserLog(userLog);	
	}

	@Override
	public void userLogout(UserLog userLog)
	{
		zhoomuditiDao.userLogout(userLog);		
	}

	@Override
	public void saveUser(User user)
	{
		zhoomuditiDao.saveUser(user);		
	}

	@Override
	public List<Category> loadCategory()
	{
		return zhoomuditiDao.loadCategory();
	}

	@Override
	public List<Item> loadItem()
	{
		return zhoomuditiDao.loadItem();
	}

	@Override
	public List<Item> loadItemById(Long id)
	{
		return zhoomuditiDao.loadItemById(id);
	}

	@Override
	public List<Item> loadItemByCategoryId(Long id, boolean enableAsc)
	{
		return zhoomuditiDao.loadItemByCategoryId(id, enableAsc);
	}

	@Override
	public void saveComment(Comment comment)
	{
		zhoomuditiDao.saveComment(comment);	
	}

	@Override
	public void reportComment(Comment comment)
	{
		zhoomuditiDao.reportComment(comment);
	}

	@Override
	public List<Comment> loadCommentByItemId(Long id)
	{
		return zhoomuditiDao.loadCommentByItemId(id);
	}

	@Override
	public List<Comment> loadCommentByCommentId(Long id)
	{
		return zhoomuditiDao.loadCommentByCommentId(id);
	}

	@Override
	public List<Item> searchItem(Long id, String criteria)
	{
		return zhoomuditiDao.searchItem(id, criteria);
	}

	@Override
	public void saveContactUs(ContactUs contactUs)
	{
		zhoomuditiDao.saveContactUs(contactUs);			
	}	
}