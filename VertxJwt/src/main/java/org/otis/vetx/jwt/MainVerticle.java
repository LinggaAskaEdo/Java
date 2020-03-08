package org.otis.vetx.jwt;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle
{
    @Override
    public void start() throws Exception
    {
        Router router = Router.router(vertx);

        // Create a JWT Auth Provider
//        JWTAuth jwt = JWTAuth.create(vertx, new JsonObject()
//                .put("keyStore", new JsonObject()
//                        .put("type", "jceks")
//                        .put("path", "keystore.jceks")
//                        .put("password", "secret")));

        JWTAuthOptions jwtAuthOptions = new JWTAuthOptions(new JsonObject()
                .put("keyStore", new JsonObject()
                        .put("type", "jceks")
                        .put("path", "keystore.jceks")
                        .put("password", "secret")));

        JWTAuth jwt = JWTAuth.create(vertx, jwtAuthOptions);

        // protect the API
        router.route("/api/*").handler(JWTAuthHandler.create(jwt, "/api/newToken"));

        // this route is excluded from the auth handler
        router.get("/api/newToken").handler(ctx -> {
            ctx.response().putHeader("Content-Type", "text/plain");
            ctx.response().end(jwt.generateToken(new JsonObject(), new JWTOptions().setExpiresInSeconds(60)));
        });

        // this is the secret API
        router.get("/api/protected").handler(ctx -> {
            ctx.response().putHeader("Content-Type", "text/plain");
            ctx.response().end("a secret you should keep for yourself...");
        });

        // Serve the non private static pages
        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }
}