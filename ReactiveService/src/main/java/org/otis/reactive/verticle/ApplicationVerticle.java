package org.otis.reactive.verticle;

import io.reactivex.Completable;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.auth.User;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.otis.reactive.dao.MySqlTodoService;
import org.otis.reactive.dao.TodoService;
import org.otis.reactive.service.RestService;

public class ApplicationVerticle extends MainVerticle
{
    private static final Logger logger = LogManager.getLogger(RestService.class);

    private TodoService todoService;
    private RestService restService;

    @Override
    public void start(Promise<Void> promise)
    {
        Router router = Router.router(vertx);

        // Enable HTTP Body Parse
        router.route().handler(BodyHandler.create());

        // Enable CORS
        enableCorsSupport(router);

//        JsonObject authInfo = new JsonObject().put("username", "tim").put("password", "mypassword");
//
//        authProvider.authenticate(authInfo, res -> {
//            if (res.succeeded()) {
//
//                User user = res.result();
//
//                System.out.println("User " + user.principal() + " is now authenticated");
//
//            } else {
//                res.cause().printStackTrace();
//            }
//        });

        router.get("/rest/test/getFlight").handler(this::handleTestGetFlight);
        router.get("/rest/getFlights").handler(this::handleTestGetFlights);

        router.get("/db/test/getTodo").handler(this::handleTestGetDb);
        router.get("/db/getTodos").handler(this::handleGetAll);

        router.get("/secure/test").handler(this::handleTestSecure);

        String host = config().getString("http.address");
        int port = config().getInteger("http.port");

        checkDatabaseConnection()
                .andThen(initializeWebClient())
                .andThen(createHttpServer(router, host, port))
                .subscribe(promise::complete, promise::fail);
    }

    private Completable checkDatabaseConnection()
    {
        todoService = new MySqlTodoService(vertx, config());

        return todoService.checkConnection();
    }

    private Completable initializeWebClient()
    {
        long restTimeout = config().getLong("rest.timeout");
        String appId = config().getString("app.id");
        String appKey = config().getString("app.key");

        WebClient webClient = WebClient.create(vertx);
        restService = new RestService(webClient, restTimeout, appId, appKey);

        return restService.checkConnection();
    }

    private void handleTestGetFlight(RoutingContext context)
    {
        sendResponse(context, "Test Get Flight (REST) !!!");
    }

    private void handleTestGetFlights(RoutingContext context)
    {
        sendResponse(context, restService.callPublicFlight());
    }

    private void handleTestGetDb(RoutingContext context)
    {
        sendResponse(context, "Test Get Todo (DB) !!!");
    }

    private void handleGetAll(RoutingContext context)
    {
        sendResponse(context, todoService.getAll());
    }

    private void handleTestSecure(RoutingContext context)
    {
        String auth = context.request().getHeader(HttpHeaders.AUTHORIZATION);

        logger.info("auth: {}", auth);

//        boolean isAuthenticated = context.user() != null;
    }
}