package org.otis.reactive;

import io.vertx.core.Promise;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.client.WebClient;
import org.otis.reactive.service.RestService;

public class MainVerticle extends AbstractVerticle
{
    @Override
    public void start(Promise<Void> promise)
    {
        vertx.createHttpServer()
                .requestHandler(createRouter())
                .listen(8080, httpServerAsyncResult -> {
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
        RestService restService = new RestService();

        /*new*/
        router.get("/getFlight").handler(routingContext -> routingContext.response().end("Test Get Flight !!!"));
        router.get("/getFlights").handler(restService.callPublicFlightAPI(webClient));

        return router;
    }
}