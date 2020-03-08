package com.springboot.vertx.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.springboot.vertx.models.Response;
import com.springboot.vertx.models.Transactions;
import com.springboot.vertx.preference.ConfigPreference;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.client.HttpRequest;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestService
{
    private static final Logger logger = LogManager.getLogger(RestService.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private ConfigPreference preference;
//    private WebClient webClient;

    @Autowired
    public RestService(ConfigPreference preference)
    {
        this.preference = preference;
    }

//    @Autowired
//    public RestService(ConfigPreference preference, WebClient webClient)
//    {
//        this.preference = preference;
//        this.webClient = webClient;
//    }

    public Handler<RoutingContext> callPublicFlightAPI(WebClient webClient)
    {
        return routingContext -> callPublicFlight(webClient, handler -> {
            try
            {
                if (handler.succeeded())
                {
                    routingContext.response()
                            .putHeader("Content-Type", "application/json")
                            .end(mapper.writeValueAsString(handler.result().body()));
                }
                else
                {
                    logger.info("Failed when callPublicFlightAPI");
                    routingContext.response().setStatusCode(500).end(handler.cause().toString());
                }
            }
            catch (Exception e)
            {
                logger.info("Error: {}", e.getMessage());

                routingContext.response()
                        .setStatusCode(500)
                        .end(e.getMessage());
            }
        });
    }

    private void callPublicFlight(WebClient webClient, Handler<AsyncResult<HttpResponse<Transactions>>> handler)
    {
        logger.info("Calling public flight endpoint at: https://api.schiphol.nl/public-flights/flights");

        HttpRequest<Transactions> request = webClient
                .getAbs("https://api.schiphol.nl/public-flights/flights")
                .addQueryParam("includedelays", "false")
                .addQueryParam("page", "0")
                .addQueryParam("sort", "scheduleTime")
                .putHeader("Accept",  "application/json")
                .putHeader("app_id", preference.appId)
                .putHeader("app_key", preference.appKey)
                .putHeader("ResourceVersion",  "v4")
                .timeout(preference.restTimeout)
                .as(BodyCodec.json(Transactions.class));

        request.send(handler);
    }

    public Handler<RoutingContext> callPublicFlightAPIV2(WebClient webClient)
    {
        Transactions transactions = callPublicFlightV2(webClient);

        if (null != transactions)
        {
            return routingContext -> routingContext
                    .response()
                    .putHeader("Content-Type", "application/json")
                    .end(new Gson().toJson(new Response<>("200", "Request was successful", transactions.getFlights())));
        }
        else
        {
            return routingContext -> routingContext
                    .response()
                    .putHeader("Content-Type", "application/json")
                    .setStatusCode(500)
                    .end(new Gson().toJson(new Response<>("500", "Failed get data from https://api.schiphol.nl/public-flights/flights")));
        }
    }

    private Transactions callPublicFlightV2(WebClient webClient)
    {
        final Transactions[] transactions = { new Transactions() };

        webClient
                .getAbs("https://api.schiphol.nl/public-flights/flights")
                .addQueryParam("includedelays", "false")
                .addQueryParam("page", "0")
                .addQueryParam("sort", "scheduleTime")
                .putHeader("Accept",  "application/json")
                .putHeader("app_id", preference.appId)
                .putHeader("app_key", preference.appKey)
                .putHeader("ResourceVersion",  "v4")
                .timeout(preference.restTimeout)
                .send(ar -> {
                    if (ar.succeeded())
                    {
                        String jsonResult = ar.result().bodyAsString();

                        try
                        {
                            Transactions transactionsResult = new Gson().fromJson(jsonResult, Transactions.class);
                            transactions[0].setFlights(transactionsResult.getFlights());
                        }
                        catch (Exception e)
                        {
                            logger.info("Error get Flights: {}", e.getMessage());
                        }
                    }
                    else
                    {
                        logger.info("Error when callPublicFlightV2");
                    }
                });

        return transactions[0];
    }
}