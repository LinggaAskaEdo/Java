package org.rest.vertx.verticle;

import com.zandero.rest.RestRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import org.rest.vertx.controller.CalculateController;
import org.rest.vertx.controller.FlightController;
import org.rest.vertx.controller.TestController;

public class RestVerticle extends AbstractVerticle
{
    private static final String HOST = "0.0.0.0";
    private static final int PORT = 8082;

    @Override
    public void start(Promise<Void> promise)
    {
        Router router = Router.router(vertx);

        TestController testController = new TestController();
        CalculateController calculateController = new CalculateController();
        FlightController flightController = new FlightController();

        RestRouter.register(router, testController);
        RestRouter.register(router, calculateController);
        RestRouter.register(router, flightController);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(PORT, HOST, httpServerAsyncResult -> {
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
}