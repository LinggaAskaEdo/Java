package ws.zp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ws.zp.constant.ServiceConstant;
import ws.zp.function.EncryptUtil;
import ws.zp.function.WSFunction;
import ws.zp.model.Category;
import ws.zp.model.Comment;
import ws.zp.model.ContactUs;
import ws.zp.model.Item;
import ws.zp.model.ReturnMessage;
import ws.zp.model.User;
import ws.zp.model.UserLog;
import ws.zp.model.UserSession;
import ws.zp.service.ZhoomuditiService;

@Configuration
@ComponentScan(basePackages = { "ws.zp" })
@PropertySource("classpath:messages.properties")
@RestController
public class ZhoomuditiRestController
{
	static final Logger log = LoggerFactory.getLogger(ZhoomuditiRestController.class);

	@Autowired
	ZhoomuditiService zhoomuditiService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	WSFunction zhoopersFunction;
	
	@Autowired
	EncryptUtil encryptUtil;	
	
	//Retrieve all user
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.LOAD_USER, method = RequestMethod.GET)
	public String loadUser()
	{
		log.info("loadUser()");
		List<User> userList = new ArrayList<User>();
		ReturnMessage returnMessage = new ReturnMessage();
		
		try
		{			
			userList = zhoomuditiService.getUser();
			log.debug("userList size: {}", userList.size());
			if (userList.isEmpty())
			{
				returnMessage.setStatus(env.getProperty("return.message.error"));				
				returnMessage.setMessage(env.getProperty("load.user.empty"));
				return new Gson().toJson(returnMessage);				
			}		
		}
		catch (Exception e)
		{
			log.error("Error allUser(): {}", e.toString());
			returnMessage.setStatus(env.getProperty("return.message.error"));			
			returnMessage.setMessage(env.getProperty("load.user.error"));
			return new Gson().toJson(returnMessage);
		}
		return new Gson().toJson(userList);		
	}
	
	//Generate Token
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.CHECK_LOGIN, method = RequestMethod.POST)
	public String checkLogin(@RequestBody User user)
	{
		log.info("checkLogin()");
		
		List<User> userResult = new ArrayList<User>();
		List<UserLog> userLogResult = new ArrayList<UserLog>();		
		ReturnMessage returnMessage = new ReturnMessage();
		UserLog userLog = new UserLog();
		UserSession userSession = new UserSession();
		boolean emptyUser = true;
		
		try
		{
			userResult = zhoomuditiService.checkExistUser(user.getUsername());
			
			if (!userResult.isEmpty())
			{
				log.info("user: {}", userResult.toString());
				
				if (user.getPassword().equalsIgnoreCase(encryptUtil.decryptString(userResult.get(0).getPassword())))
				{
					userLogResult = zhoomuditiService.checkToken(userResult.get(0).getUsers_id());
					
					if (!userLogResult.isEmpty())
					{
						if (userLogResult.get(0).isExpired() == false)
						{
							log.info("User Still Login !!!");
							returnMessage.setStatus(env.getProperty("return.message.error"));
							returnMessage.setMessage(env.getProperty("check.login.still.login"));
							return new Gson().toJson(returnMessage);
						}
					}
					
					//set parameter for UserLog
					String token = zhoopersFunction.generateToken();
					userLog.setUsers_id(userResult.get(0).getUsers_id());
					userLog.setToken(token);
					userLog.setExpired(false);
					userLog.setLast_attempt(new Date());
					zhoomuditiService.addUserLog(userLog);
						
					log.info("userLog: {}", userLog.toString());
						
					//set parameter for UserSession
					userSession.setUsername(userResult.get(0).getUsername());
					userSession.setToken(token);
						
					emptyUser = false;
				
				}			
			}			
			
			if (emptyUser)
			{
				log.info("userResult.size: {}", userResult.size());
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("check.login.empty"));
				return new Gson().toJson(returnMessage);
			}
		}
		catch (Exception e)
		{
			log.error("Error checkLogin(): {}", e.toString());
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("check.login.failed"));
			return new Gson().toJson(returnMessage);
		}
		
		return new Gson().toJson(userSession);		
	}
	
	//Logout
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.CHECK_LOGOUT, method = RequestMethod.POST)
	public String checkLogout(@RequestBody UserSession userSession)
	{
		log.info("checkLogout()");
		
		List<User> userResult = new ArrayList<User>();
		List<UserLog> userLogResult = new ArrayList<UserLog>();
		ReturnMessage returnMessage = new ReturnMessage();
		UserLog userLog = new UserLog();		
		boolean logoutStatus = true;
		
		try
		{
			if (!(userSession.getToken().isEmpty()) && !(userSession.getUsername().isEmpty()))
			{
				userResult = zhoomuditiService.checkExistUser(userSession.getUsername());
				
				if (!userResult.isEmpty())
				{
					log.info("user: {}", userResult.toString());
					
					//select userLog where token & users_id
					userLogResult = zhoomuditiService.findSession(userResult.get(0).getUsers_id(), userSession.getToken());
					
					log.info("userLog: {}", userLogResult.toString());
					
					if (!userLogResult.isEmpty())
					{
						//update userLog.expired to true where token & users_id
						userLog.setUsers_log_id(userLogResult.get(0).getUsers_log_id());
						userLog.setUsers_id(userLogResult.get(0).getUsers_id());
						userLog.setToken(userLogResult.get(0).getToken());
						userLog.setExpired(true);
						userLog.setLast_attempt(userLogResult.get(0).getLast_attempt());
						zhoomuditiService.userLogout(userLog);
						
						//if succes update
						log.info("Logout success !!!");
						returnMessage.setStatus(env.getProperty("return.message.success"));
						returnMessage.setMessage(env.getProperty("check.logout.success"));
						
						logoutStatus = false;
					}				
				}
			}
			else
			{
				log.info("Invalid format request !!!");
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("check.logout.invalid.request"));						
				logoutStatus = false;
			}
			
			if (logoutStatus)
			{
				//return json message: status=0, message=invalid format request
				log.info("userResult.size: {}", userResult.size());
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("check.logout.failed"));								
			}
		}
		catch (Exception e)
		{
			log.error("Error checkLogin(): {}", e.toString());
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("check.logout.failed"));					
		}	
		
		return new Gson().toJson(returnMessage);		
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.REGISTER, method = RequestMethod.POST)
	public String register(@RequestBody User user)
	{
		log.info("register()");
		
		List<User> userResult = new ArrayList<User>();
		User userSave = new User();
		ReturnMessage returnMessage = new ReturnMessage();
		
		try
		{
			userResult = zhoomuditiService.checkExistUser(user.getUsername());
			
			if (userResult.isEmpty())
			{
				userSave.setUsername(user.getUsername());
				userSave.setPassword(encryptUtil.encryptString(user.getPassword()));
				userSave.setFull_name(user.getFull_name());
				userSave.setPhone_number(user.getPhone_number());
				userSave.setCompany_name(user.getCompany_name());
				userSave.setCompany_address(user.getCompany_address());
				userSave.setCompany_phone_number(user.getCompany_phone_number());
				userSave.setBilling_address(user.getBilling_address());
				userSave.setShipping_address(user.getShipping_address());			
				zhoomuditiService.saveUser(userSave);
				
				log.info("Register Success, user: {}", user.toString());				
				returnMessage.setStatus(env.getProperty("return.message.success"));
				returnMessage.setMessage(env.getProperty("register.success"));
			}
			else
			{
				log.error("User is exist: {}", userResult.toString());			
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("register.fail"));
			}
		}
		catch (Exception e)
		{
			log.error("Error checkLogin(): {}", e.toString());			
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("register.fail"));
		}
		
		return new Gson().toJson(returnMessage);		
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.LOAD_CATEGORY, method = RequestMethod.POST)
	public String loadCategory()
	{
		log.info("loadCategory()");
		
		List<Category> categoryResult = new ArrayList<Category>();
		ReturnMessage returnMessage = new ReturnMessage();
		String jsonResult;
		
		try
		{
			categoryResult = zhoomuditiService.loadCategory();
			
			if (!categoryResult.isEmpty())
			{
				log.info("categoryResult: {}", categoryResult.toString());
				jsonResult = new Gson().toJson(categoryResult);
			}
			else
			{
				log.info("Category is empty !!!");
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("load.category.empty"));
				return new Gson().toJson(returnMessage);
			}
		}
		catch (Exception e)
		{
			log.error("Error category(): {}", e.toString());
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("load.category.fail"));
			return new Gson().toJson(returnMessage);
		}
		
		return jsonResult;
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.LOAD_ITEM, method = RequestMethod.POST)
	public String loadItem()
	{
		log.info("loadItem()");
		
		List<Item> itemResult = new ArrayList<Item>();
		List<Item> itemAll = new ArrayList<Item>();
		ReturnMessage returnMessage = new ReturnMessage();
		String jsonResult;
		
		try
		{
			itemResult = zhoomuditiService.loadItem();
			
			log.info("itemResult: {}, {}", itemResult.size(), itemResult.toString());
			
			if (!itemResult.isEmpty())
			{
				for (int i = 0; i < itemResult.size(); i++)
				{
					log.info(itemResult.get(i).getItem_name());					
					Item item = new Item(itemResult.get(i).getItem_id(), itemResult.get(i).getCategory_id(), itemResult.get(i).getItem_name(), 
							itemResult.get(i).getItem_description(), itemResult.get(i).getItem_image_path_1());					
					itemAll.add(item);
				}
				jsonResult = new Gson().toJson(itemAll);
			}
			else
			{
				log.info("Item is empty !!!");				
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("load.item.empty"));
				return new Gson().toJson(returnMessage);
			}			
		}
		catch (Exception e)
		{
			log.error("Error loadItem(): {}", e.toString());			
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("load.item.fail"));
			return new Gson().toJson(returnMessage);
		}
		
		return jsonResult;		
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.LOAD_ITEM_BY_ID + "/{id}", method = RequestMethod.GET)
	public String loadItemById(@PathVariable("id") Long id)	
	{
		log.info("loadItemById()");
		
		List<Item> itemResult = new ArrayList<Item>();
		ReturnMessage returnMessage = new ReturnMessage();
		String jsonResult;
		
		try
		{
			itemResult = zhoomuditiService.loadItemById(id);			
			
			log.info("itemResult: {}, {}", itemResult.size(), itemResult.toString());
			
			if (!itemResult.isEmpty())
			{
				jsonResult = new Gson().toJson(itemResult);
			}
			else
			{
				log.info("Item is empty !!!");				
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("load.item.by.id.empty"));
				return new Gson().toJson(returnMessage);
			}
		}
		catch (Exception e)
		{
			log.error("Error category(): {}", e.toString());			
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("load.item.by.id.fail"));
			return new Gson().toJson(returnMessage);
		}
		
		return jsonResult;		
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.LOAD_ITEM_BY_CATEGORY_ID + "/{id}/{enableAsc}", method = RequestMethod.GET)
	public String loadItemByCategoryId(@PathVariable("id") Long id, @PathVariable("enableAsc") String enableAsc)	
	{
		log.info("loadItemByCategoryId()");
		
		List<Item> itemResult = new ArrayList<Item>();
		ReturnMessage returnMessage = new ReturnMessage();
		String jsonResult;
		
		try
		{
			itemResult = zhoomuditiService.loadItemByCategoryId(id, Boolean.valueOf(enableAsc));			
			
			log.info("itemResult: {}, {}", itemResult.size(), itemResult.toString());
			
			if (!itemResult.isEmpty())
			{
				jsonResult = new Gson().toJson(itemResult);
			}
			else
			{
				log.info("Item is empty !!!");				
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("load.item.by.category.id.empty"));
				return new Gson().toJson(returnMessage);
			}
		}
		catch (Exception e)
		{
			log.error("Error category(): {}", e.toString());			
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("load.item.by.category.id.fail"));
			return new Gson().toJson(returnMessage);
		}
		
		return jsonResult;
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.SAVE_COMMENT + "/{username}/{token}", method = RequestMethod.POST)	
	public String saveComment(@PathVariable("username") String username, @PathVariable("token") String token, @RequestBody Comment comment)	
	{
		log.info("saveComment()");
		
		List<User> userResult = new ArrayList<User>();
		List<UserLog> userLogResult = new ArrayList<UserLog>();
		Comment commentSave = new Comment();
		ReturnMessage returnMessage = new ReturnMessage();
			
		try
		{
			if (!username.isEmpty() && !token.isEmpty() && username != null && token != null)
			{
				//get id from username
				userResult = zhoomuditiService.checkExistUser(username);				
				
				log.info("userResult: {}", userResult.toString());
				
				if (!userResult.isEmpty())
				{
					//check expired token, select userLog where token & users_id
					userLogResult = zhoomuditiService.findSession(userResult.get(0).getUsers_id(), token);					
					
					log.info("userLog: {}", userLogResult.toString());
					
					if (!userLogResult.isEmpty() && userLogResult.get(0).isExpired() == false)
					{
						commentSave.setUsers_id(userResult.get(0).getUsers_id());
						commentSave.setItem_id(comment.getItem_id());				
						commentSave.setComment_text(comment.getComment_text());
						commentSave.setComment_parent_id(comment.getComment_parent_id());
						commentSave.setComment_date(new Date());
						commentSave.setStatus_report(false);
						
						zhoomuditiService.saveComment(commentSave);
						
						log.info("Comment success save, comment: {}", comment.toString());
						returnMessage.setStatus(env.getProperty("return.message.success"));
						returnMessage.setMessage(env.getProperty("save.comment.success"));
					}
					else
					{
						log.info("Please login before comment !!!");
						returnMessage.setStatus(env.getProperty("return.message.error"));
						returnMessage.setMessage(env.getProperty("save.comment.fail.login"));
					}					
				}
				else
				{
					log.info("Please sign up before comment !!!");
					returnMessage.setStatus(env.getProperty("return.message.error"));
					returnMessage.setMessage(env.getProperty("save.comment.fail.signup"));
				}
			}
			else
			{
				log.info("Please sign up before comment !!!");
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("save.comment.fail.login"));
			}					
		}
		catch (Exception e)
		{
			log.error("Error comment(): {}", e.toString());
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("save.comment.fail"));
		}
		
		return new Gson().toJson(returnMessage);		
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.REPORT_COMMENT + "/{username}/{token}", method = RequestMethod.POST)
	public String reportComment(@PathVariable("username") String username, @PathVariable("token") String token, @RequestBody Comment comment)
	{
		log.info("reportComment()");
		
		List<User> userResult = new ArrayList<User>();
		List<UserLog> userLogResult = new ArrayList<UserLog>();
		List<Comment> commentResult = new ArrayList<Comment>();
		Comment commentUpdate = new Comment();
		ReturnMessage returnMessage = new ReturnMessage();
			
		try
		{
			if (!username.isEmpty() && !token.isEmpty() && username != null && token != null)
			{
				//get id from username
				userResult = zhoomuditiService.checkExistUser(username);
				
				log.info("userResult: {}", userResult.toString());
				
				if (!userResult.isEmpty())
				{
					//check expired token, select userLog where token & users_id
					userLogResult = zhoomuditiService.findSession(userResult.get(0).getUsers_id(), token);
					
					log.info("userLog: {}", userLogResult.toString());
					
					if (!userLogResult.isEmpty() && userLogResult.get(0).isExpired() == false)
					{
						//load comment with comment_id
						commentResult = zhoomuditiService.loadCommentByCommentId(comment.getComment_id());
						
						log.info("commentResult: {}", commentResult.toString());
						
						if (!commentResult.isEmpty())
						{
							commentUpdate.setComment_id(commentResult.get(0).getComment_id());
							commentUpdate.setUsers_id(commentResult.get(0).getUsers_id());
							commentUpdate.setItem_id(commentResult.get(0).getItem_id());
							commentUpdate.setComment_text(commentResult.get(0).getComment_text());
							commentUpdate.setComment_parent_id(commentResult.get(0).getComment_parent_id());
							commentUpdate.setComment_date(commentResult.get(0).getComment_date());							
							commentUpdate.setStatus_report(true);
							
							zhoomuditiService.reportComment(commentUpdate);
							
							log.info("Report success, comment: {}", comment.toString());
							returnMessage.setStatus(env.getProperty("return.message.success"));
							returnMessage.setMessage(env.getProperty("report.comment.success"));
						}
						else
						{
							log.info("Comment not found !!!");
							returnMessage.setStatus(env.getProperty("return.message.error"));
							returnMessage.setMessage(env.getProperty("report.comment.not.found"));
						}						
					}
					else
					{
						log.info("Please login before report !!!");						
						returnMessage.setStatus(env.getProperty("return.message.error"));
						returnMessage.setMessage(env.getProperty("report.comment.fail.login"));
					}					
				}
				else
				{
					log.info("Please sign up before report !!!");					
					returnMessage.setStatus(env.getProperty("return.message.error"));
					returnMessage.setMessage(env.getProperty("report.comment.fail.signup"));
				}
			}
			else
			{
				log.info("Please login before report !!!");
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("report.comment.fail.login"));
			}					
		}
		catch (Exception e)
		{
			log.error("Error comment(): {}", e.toString());
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("report.comment.fail"));
		}
		
		return new Gson().toJson(returnMessage);
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.LOAD_COMMENT_BY_ITEM_ID + "/{id}", method = RequestMethod.GET)
	public String loadCommentByItemId(@PathVariable("id") Long id)
	{
		log.info("loadCommentByItemId()");
		
		List<Comment> commentResult = new ArrayList<Comment>();
		ReturnMessage returnMessage = new ReturnMessage();
		String jsonResult;
		
		try
		{
			commentResult = zhoomuditiService.loadCommentByItemId(id);
			
			if (!commentResult.isEmpty())
			{
				log.info("commentResult: {}", commentResult.toString());
				jsonResult = new Gson().toJson(commentResult);
			}
			else
			{
				log.info("Comment is empty !!!");				
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("load.comment.by.item.id.empty"));
				return new Gson().toJson(returnMessage);
			}
		}
		catch (Exception e)
		{
			log.error("Error comment(): {}", e.toString());			
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("load.comment.by.item.id.fail"));
			return new Gson().toJson(returnMessage);
		}
		
		return jsonResult;	
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.SEARCH_ITEM + "/{id}/{criteria}", method = RequestMethod.GET)
	public String searchItem(@PathVariable("id") Long id, @PathVariable("criteria") String criteria)	
	{
		log.info("searchItem()");
		
		List<Item> itemResult = new ArrayList<Item>();
		ReturnMessage returnMessage = new ReturnMessage();
		String jsonResult = null;
		
		try
		{
			if (criteria.equalsIgnoreCase("''"))
			{
				criteria = "";
			}
			
			itemResult = zhoomuditiService.searchItem(id, criteria);
			
			if (!itemResult.isEmpty())
			{
				log.info("itemResult: {}", itemResult.toString());
				jsonResult = new Gson().toJson(itemResult);
			}
			else
			{
				log.info("Search item is empty !!!");				
				returnMessage.setStatus(env.getProperty("return.message.error"));
				returnMessage.setMessage(env.getProperty("search.item.empty"));
				return new Gson().toJson(returnMessage);
			}
		}
		catch (Exception e)
		{
			log.error("Error search item(): {}", e.toString());			
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("search.item.error"));
			return new Gson().toJson(returnMessage);
		}
		
		return jsonResult;		
	}
	
	@RequestMapping(value = ServiceConstant.WS_ZHMDT + "/" + ServiceConstant.CONTACT_US, method = RequestMethod.POST)
	public String register(@RequestBody ContactUs contactUs)
	{
		log.info("contactUs()");
		
		ReturnMessage returnMessage = new ReturnMessage();
		
		try
		{
			zhoomuditiService.saveContactUs(contactUs);
			log.info("Save Contact Us Success, contactUs: {}", contactUs.toString());				
			returnMessage.setStatus(env.getProperty("return.message.success"));
			returnMessage.setMessage(env.getProperty("contact.us.success"));			
		}
		catch (Exception e)
		{
			log.error("Error contactUs(): {}", e.toString());			
			returnMessage.setStatus(env.getProperty("return.message.error"));
			returnMessage.setMessage(env.getProperty("contact.us.fail"));
		}
		
		return new Gson().toJson(returnMessage);		
	}
}