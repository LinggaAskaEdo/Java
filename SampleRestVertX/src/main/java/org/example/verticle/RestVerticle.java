package org.example.verticle;

import com.zandero.rest.RestRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import org.example.controller.CalculateController;
import org.example.controller.TestController;

public class RestVerticle extends AbstractVerticle
{
    private static final String HOST = "0.0.0.0";
    private static final int PORT = 8082;

    @Override
    public void start(Promise<Void> startPromise)
    {
        Router router = Router.router(vertx);

        TestController testController = new TestController();
        CalculateController calculateController = new CalculateController();

        RestRouter.register(router, testController);
        RestRouter.register(router, calculateController);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(PORT, HOST);
    }
}