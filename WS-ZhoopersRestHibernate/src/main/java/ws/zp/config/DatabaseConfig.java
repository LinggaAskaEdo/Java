package ws.zp.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("ws.zp")
@PropertySource("classpath:database.properties")
public class DatabaseConfig 
{
	private static final String PROPERTY_NAME_DATABASE_DRIVER  	= "db.driver";
	
	private static final String PROPERTY_NAME_DATABASE_URL_ZHMDT      = "db.url.zhmdt";
	private static final String PROPERTY_NAME_DATABASE_USERNAME_ZHMDT = "db.username.zhmdt";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD_ZHMDT = "db.password.zhmdt";
	
	private static final String PROPERTY_NAME_DATABASE_URL_ZHPRS      = "db.url.zhprs";
	private static final String PROPERTY_NAME_DATABASE_USERNAME_ZHPRS = "db.username.zhprs";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD_ZHPRS = "db.password.zhprs";
	
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT 			 = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL 			 = "hibernate.show_sql";
	private static final String PROPERTY_NAME_C3P0_MIN_SIZE 				 = "hibernate.c3p0.min_size";
	private static final String PROPERTY_NAME_C3P0_MAX_SIZE 				 = "hibernate.c3p0.max_size";
	private static final String PROPERTY_NAME_C3P0_TIMEOUT 					 = "hibernate.c3p0.timeout";
	private static final String PROPERTY_NAME_C3P0_MAX_STATEMENTS 			 = "hibernate.c3p0.max_statements";
	private static final String PROPERTY_NAME_C3P0_IDLE_TEST 				 = "hibernate.c3p0.idle_test_period";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	     
	@Resource
	private Environment env;
	  
	@Bean(name = "dataSourceZhmdt")
	@Primary
	public DataSource dataSourceZhmdt() 
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL_ZHMDT));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME_ZHMDT));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD_ZHMDT));
		return dataSource;
	}
	
	@Bean(name = "dataSourceZhprs")
	public DataSource dataSourceZhprs()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL_ZHPRS));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME_ZHPRS));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD_ZHPRS));
		return dataSource;
	}
	  
	private Properties hibernatePropertiesZhmdt() 
	{
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_C3P0_MIN_SIZE, env.getRequiredProperty(PROPERTY_NAME_C3P0_MIN_SIZE));
	    properties.put(PROPERTY_NAME_C3P0_MAX_SIZE, env.getRequiredProperty(PROPERTY_NAME_C3P0_MAX_SIZE));
	    properties.put(PROPERTY_NAME_C3P0_TIMEOUT, env.getRequiredProperty(PROPERTY_NAME_C3P0_TIMEOUT));
	    properties.put(PROPERTY_NAME_C3P0_MAX_STATEMENTS, env.getRequiredProperty(PROPERTY_NAME_C3P0_MAX_STATEMENTS));
	    properties.put(PROPERTY_NAME_C3P0_IDLE_TEST, env.getRequiredProperty(PROPERTY_NAME_C3P0_IDLE_TEST));
		return properties; 
	}
	
	private Properties hibernatePropertiesZhprs() 
	{
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_C3P0_MIN_SIZE, env.getRequiredProperty(PROPERTY_NAME_C3P0_MIN_SIZE));
	    properties.put(PROPERTY_NAME_C3P0_MAX_SIZE, env.getRequiredProperty(PROPERTY_NAME_C3P0_MAX_SIZE));
	    properties.put(PROPERTY_NAME_C3P0_TIMEOUT, env.getRequiredProperty(PROPERTY_NAME_C3P0_TIMEOUT));
	    properties.put(PROPERTY_NAME_C3P0_MAX_STATEMENTS, env.getRequiredProperty(PROPERTY_NAME_C3P0_MAX_STATEMENTS));
	    properties.put(PROPERTY_NAME_C3P0_IDLE_TEST, env.getRequiredProperty(PROPERTY_NAME_C3P0_IDLE_TEST));
		return properties; 
	}
	  
	@Bean(name = "transactionManagerZhmdt")
	@Primary
	public HibernateTransactionManager transactionManagerZhmdt() 
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactoryZhmdt().getObject());
		return transactionManager;
	}
	  
	@Bean(name = "transactionManagerZhprs")
	public HibernateTransactionManager transactionManagerZhprs() 
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactoryZhprs().getObject());
		return transactionManager;
	}
	
	@Bean(name = "sessionFactoryZhmdt")
	public LocalSessionFactoryBean sessionFactoryZhmdt() 
	{
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSourceZhmdt());
		sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		sessionFactoryBean.setHibernateProperties(hibernatePropertiesZhmdt());
		return sessionFactoryBean;
	}	
	  
	@Bean(name = "sessionFactoryZhprs")
	public LocalSessionFactoryBean sessionFactoryZhprs() 
	{
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSourceZhprs());
		sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		sessionFactoryBean.setHibernateProperties(hibernatePropertiesZhprs());
		return sessionFactoryBean;
	}
}