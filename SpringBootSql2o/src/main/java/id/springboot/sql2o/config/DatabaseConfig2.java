//package id.springboot.sql2o.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//import org.sql2o.Sql2o;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//public class DatabaseConfig2 implements TransactionManagementConfigurer
//{
//    private EntityManager entityManager;
//
//    @Autowired
//    public DatabaseConfig2(EntityManager entityManager)
//    {
//        this.entityManager = entityManager;
//    }
//
//    @PostConstruct
//    public DataSource dataSource()
//    {
//        EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();
//
//        Map<String, Object> properties = entityManagerFactory.getProperties();
//
//        return (DataSource) properties.get("javax.persistence.jtaDataSource");
//    }
//
//    @Bean
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager()
//    {
//        DataSource dataSource = dataSource();
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Autowired
//    @Bean
//    public Sql2o sql2oPrimary(DataSource dataSource)
//    {
//        return new Sql2o(dataSource);
//    }
//}