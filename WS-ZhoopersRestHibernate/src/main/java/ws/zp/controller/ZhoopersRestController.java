package ws.zp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ws.zp.constant.ServiceConstant;
import ws.zp.model.Province;
import ws.zp.model.ReturnMessage;
import ws.zp.service.ZhoopersService;

@Configuration
@ComponentScan(basePackages = { "ws.zp" })
@PropertySource("classpath:messages.properties")
@RestController
public class ZhoopersRestController
{
	static final Logger log = LoggerFactory.getLogger(ZhoopersRestController.class);
	
	@Autowired
	ZhoopersService zhoopersService;
	
	@Autowired
	private Environment env;
	
	//Retrieve all province
	@RequestMapping(value = ServiceConstant.WS_ZHPRS + "/" + ServiceConstant.LOAD_PROVINCE, method = RequestMethod.GET)
	public String loadUser()
		{
			log.info("loadProvince()");
			List<Province> provinceList = new ArrayList<Province>();
			ReturnMessage returnMessage = new ReturnMessage();
			
			try
			{			
				provinceList = zhoopersService.loadProvince();
				log.debug("provinceList size: {}", provinceList.size());
				if (provinceList.isEmpty())
				{
					returnMessage.setStatus(env.getProperty("return.message.error"));				
					returnMessage.setMessage(env.getProperty("load.province.empty"));
					return new Gson().toJson(returnMessage);				
				}		
			}
			catch (Exception e)
			{
				log.error("Error allProvince(): {}", e.toString());
				returnMessage.setStatus(env.getProperty("return.message.error"));			
				returnMessage.setMessage(env.getProperty("load.province.error"));
				return new Gson().toJson(returnMessage);
			}
			return new Gson().toJson(provinceList);		
		}
}