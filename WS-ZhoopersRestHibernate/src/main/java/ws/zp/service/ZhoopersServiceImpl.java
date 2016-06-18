package ws.zp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.zp.dao.ZhoopersDao;
import ws.zp.model.Province;

@Service
@Transactional
public class ZhoopersServiceImpl implements ZhoopersService
{
	@Autowired
	private ZhoopersDao zhoopersDao;
	
	@Override
	public List<Province> loadProvince()
	{		
		return zhoopersDao.loadProvince();
	}
}