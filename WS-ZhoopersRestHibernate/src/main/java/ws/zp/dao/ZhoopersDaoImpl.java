package ws.zp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ws.zp.model.Province;

@Repository
@Transactional(value="transactionManagerZhprs", readOnly = true)
public class ZhoopersDaoImpl implements ZhoopersDao
{
	@Autowired
	@Qualifier("sessionFactoryZhprs")
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Province> loadProvince()
	{
		return getCurrentSession().createQuery("from Province").list();
	}
}