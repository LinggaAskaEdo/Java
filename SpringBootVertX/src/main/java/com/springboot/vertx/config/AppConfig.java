//package com.springboot.vertx.config;
//
//import com.springboot.vertx.preference.ConfigPreference;
//import io.vertx.core.json.JsonObject;
//import io.vertx.mysqlclient.MySQLConnectOptions;
//import io.vertx.reactivex.core.Vertx;
//import io.vertx.reactivex.ext.jdbc.JDBCClient;
//import io.vertx.reactivex.ext.web.client.WebClient;
//import io.vertx.reactivex.mysqlclient.MySQLPool;
//import io.vertx.sqlclient.PoolOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig
//{
//    private ConfigPreference preference;
//
//    @Autowired
//    public AppConfig(ConfigPreference preference)
//    {
//        this.preference = preference;
//    }
//
//    @Bean
//    public WebClient webClient()
//    {
//        return WebClient.create(Vertx.vertx());
//    }
//
//    @Bean
//    public JDBCClient jdbcClient()
//    {
//        JsonObject config = new JsonObject()
//                .put("url", preference.databaseUrl)
//                .put("driver_class", preference.databaseDriver)
//                .put("user", preference.databaseUser)
//                .put("password", preference.databasePassword)
//                .put("max_pool_size", preference.databasePool);
//
//        return JDBCClient.createShared(Vertx.vertx(), config);
//    }
//
//    @Bean
//    public MySQLPool mySQLPool()
//    {
//        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
//                .setPort(preference.databasePort)
//                .setHost(preference.databaseHost)
//                .setDatabase(preference.databaseName)
//                .setUser(preference.databaseUser)
//                .setPassword(preference.databasePassword);
//
//        // Pool options
//        PoolOptions poolOptions = new PoolOptions().setMaxSize(preference.databasePool);
//
//        // Create the client pool
//        return MySQLPool.pool(connectOptions, poolOptions);
//    }
//}