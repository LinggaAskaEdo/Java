//package id.springboot.sql2o.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.sql2o.Sql2o;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Map;
//
//@Configuration
//public class DatabaseConfig
//{
//    private EntityManager entityManager;
//
//    @Autowired
//    public DatabaseConfig(EntityManager entityManager)
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
//    @Autowired
//    @Bean
//    public Sql2o sql2oPrimary(@Qualifier("dataSource") DataSource dataSource)
//    {
//        return new Sql2o(dataSource);
//    }
//}