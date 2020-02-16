package io.vertx.data.service;

import io.reactivex.Single;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.data.entity.MovieInfo;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

import java.util.concurrent.atomic.AtomicReference;

public class RestTodoService
{
    private static final Logger logger = LoggerFactory.getLogger(RestTodoService.class);

    private Vertx vertx;

    public RestTodoService(Vertx vertx)
    {
        this.vertx = vertx;
    }

    public Single<MovieInfo> getCertain(String movieId)
    {
        WebClient client = WebClient.create(vertx);
        AtomicReference<MovieInfo> movieInfo = new AtomicReference<>(new MovieInfo());

        client
                .get("http://www.omdbapi.com")
                .addQueryParam("i", movieId)
                .addQueryParam("apikey", "d56bdf3e")
                .as(BodyCodec.json(MovieInfo.class))
                .timeout(5000)
                .send(ar -> {
                    if (ar.succeeded())
                    {
                        // Obtain response
                        HttpResponse<MovieInfo> response = ar.result();

                        movieInfo.set(response.body());
                    }
                    else
                    {
                        logger.error("Something went wrong {}", ar.cause().getMessage());
                    }
                });

        return movieInfo.get();
    }
}