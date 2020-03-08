package org.otis.reactive.service;

import io.reactivex.Completable;
import io.vertx.reactivex.ext.web.client.HttpRequest;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.otis.reactive.models.Transactions;

public class RestService
{
    private static final Logger logger = LogManager.getLogger(RestService.class);

    private final WebClient webClient;
    private final long timeout;
    private final String appId;
    private final String appKey;

    public RestService(WebClient webClient, long timeout, String appId, String appKey)
    {
        this.webClient = webClient;
        this.timeout = timeout;
        this.appId = appId;
        this.appKey = appKey;
    }

    public Completable checkConnection()
    {
        return Completable.complete();
    }

    public HttpRequest<Transactions> callPublicFlight()
    {
        logger.info("Calling public flight endpoint at: https://api.schiphol.nl/public-flights/flights");

        return webClient
                .getAbs("https://api.schiphol.nl/public-flights/flights")
                .addQueryParam("includedelays", "false")
                .addQueryParam("page", "0")
                .addQueryParam("sort", "scheduleTime")
                .putHeader("Accept",  "application/json")
                .putHeader("app_id", appId)
                .putHeader("app_key", appKey)
                .putHeader("ResourceVersion",  "v4")
                .timeout(timeout)
                .as(BodyCodec.json(Transactions.class));
    }
}