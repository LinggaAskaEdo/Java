package org.otis.reactive.verticle;

import com.google.gson.Gson;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.client.HttpRequest;
import io.vertx.reactivex.ext.web.handler.CorsHandler;
import org.otis.reactive.models.Response;
import org.otis.reactive.models.Todo;
import org.otis.reactive.models.Transactions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainVerticle extends AbstractVerticle
{
    protected Completable createHttpServer(Router router, String host, int port)
    {
        return vertx.createHttpServer()
                .requestHandler(router)
                .rxListen(port, host)
                .ignoreElement();
    }

    protected void enableCorsSupport(Router router)
    {
        // Allow Headers
        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");

        // CORS support
        router.route().handler(CorsHandler.create("*")
                .allowedHeaders(allowHeaders)
                .allowedMethod(HttpMethod.GET)
        );
    }

    protected void sendResponse(RoutingContext context, String value)
    {
        context.response().end(value);
    }

    protected void sendResponse(RoutingContext context, Single<List<Todo>> asyncResult)
    {
        if (asyncResult == null)
        {
            noContent(context);
        }
        else
        {
            asyncResult.subscribe(r -> success(context, r), ex -> internalError(context, ex));
        }
    }

    protected void sendResponse(RoutingContext context, HttpRequest<Transactions> httpRequest)
    {
        httpRequest.send(handler -> {
            if (handler.succeeded())
            {
                success(context, handler.result().body());
            }
            else
            {
                internalError(context, handler.cause());
            }
        });
    }

    protected void success(RoutingContext context, List<Todo> todoList)
    {
        context.response().setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(new Gson().toJson(new Response("200", "Request was successful", todoList)));
    }

    protected void success(RoutingContext context, Transactions transactions)
    {
        context.response().setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(new Gson().toJson(new Response("200", "Request was successful", transactions)));
    }

    private void noContent(RoutingContext context)
    {
        context.response().setStatusCode(204).end();
    }

    private void internalError(RoutingContext context, Throwable ex)
    {
        context.response().setStatusCode(500)
                .putHeader("content-type", "application/json")
                .end(new Gson().toJson(new Response("500", ex.getMessage())));
    }
}