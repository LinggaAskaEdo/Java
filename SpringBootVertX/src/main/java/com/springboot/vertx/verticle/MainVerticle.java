package com.springboot.vertx.verticle;

import com.springboot.vertx.dao.TestDao;
import com.springboot.vertx.preference.ConfigPreference;
import com.springboot.vertx.service.DatabaseService;
import com.springboot.vertx.service.RestService;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.jdbc.JDBCClient;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.mysqlclient.MySQLPool;
import io.vertx.reactivex.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainVerticle extends AbstractVerticle
{
    private ConfigPreference preference;
    private RestService restService;
    private DatabaseService databaseService;
    private TestDao testDao;

    @Autowired
    public MainVerticle(ConfigPreference preference, RestService restService, DatabaseService databaseService)
    {
        this.preference = preference;
        this.restService = restService;
        this.databaseService = databaseService;
    }

    @Override
    public void start(Promise<Void> promise)
    {
        vertx.createHttpServer()
                .requestHandler(createRouter())
                .listen(preference.httpPort, httpServerAsyncResult -> {
                    if (httpServerAsyncResult.succeeded())
                    {
                        promise.complete();
                    }
                    else
                    {
                        promise.fail(httpServerAsyncResult.cause());
                    }
                });
    }

    private Router createRouter()
    {
        Router router = Router.router(vertx);

        WebClient webClient = WebClient.create(vertx);

        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(preference.databasePort)
                .setHost(preference.databaseHost)
                .setDatabase(preference.databaseName)
                .setUser(preference.databaseUser)
                .setPassword(preference.databasePassword);

        // Pool options
        PoolOptions poolOptions = new PoolOptions().setMaxSize(preference.databasePool);

        // Create the client pool
        MySQLPool client = MySQLPool.pool(connectOptions, poolOptions);

        Pool pool = MySQLPool.pool(vertx, new MySQLConnectOptions()
                .setPort(5432)
                .setHost("the-host")
                .setDatabase("the-db")
                .setUser("user")
                .setPassword("secret"), new PoolOptions().setMaxSize(4));

        /*Router REST API*/
        router.get("/getFlight").handler(routingContext -> routingContext.response().end("Test Get Flight !!!"));
        router.get("/getFlights").handler(restService.callPublicFlightAPI(webClient));
        router.get("/getFlightsV2").handler(restService.callPublicFlightAPIV2(webClient));

        /*Router REST DB*/
        router.get("/getTitle").handler(databaseService.callDatabase(client));

        return router;
    }
}