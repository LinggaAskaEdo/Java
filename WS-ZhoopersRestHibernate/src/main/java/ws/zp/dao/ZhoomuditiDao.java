package ws.zp.dao;

import java.util.List;

import ws.zp.model.Category;
import ws.zp.model.Comment;
import ws.zp.model.ContactUs;
import ws.zp.model.Item;
import ws.zp.model.User;
import ws.zp.model.UserLog;

public interface ZhoomuditiDao
{
	public List<User> getUser();
	public List<User> checkExistUser(String username);
	public List<User> checkLogin(String username, String password);
	public List<UserLog> checkToken(Long id);
	public List<UserLog> findSession(Long id, String token);
	public void addUserLog(UserLog userLog);
	public void userLogout(UserLog userLog);
	public void saveUser(User user);
	public List<Category> loadCategory();
	public List<Item> loadItem();
	public List<Item> loadItemById(Long id);
	public List<Item> loadItemByCategoryId(Long id, boolean enableAsc);
	public void saveComment(Comment comment);
	public void reportComment(Comment comment);
	public List<Comment> loadCommentByItemId(Long id);
	public List<Comment> loadCommentByCommentId(Long id);
	public List<Item> searchItem(Long id, String criteria);
	public void saveContactUs(ContactUs contactUs);
}