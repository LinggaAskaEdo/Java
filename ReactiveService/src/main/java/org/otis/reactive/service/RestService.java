package org.otis.reactive.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.client.HttpRequest;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.otis.reactive.models.Flights;

public class RestService
{
    private static final Logger logger = LogManager.getLogger(RestService.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String APP_ID = "39000fe1";
    private static final String APP_KEY = "a975ed6958a68f9286ea3a20016348a2";

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

    private void callPublicFlight(WebClient webClient, Handler<AsyncResult<HttpResponse<Flights>>> handler)
    {
        logger.info("Calling public flight endpoint at: https://api.schiphol.nl/public-flights/flights");

        HttpRequest<Flights> request = webClient
                .getAbs("https://api.schiphol.nl/public-flights/flights")
                .addQueryParam("includedelays", "false")
                .addQueryParam("page", "0")
                .addQueryParam("sort", "scheduleTime")
                .putHeader("Accept",  "application/json")
                .putHeader("app_id", APP_ID)
                .putHeader("app_key", APP_KEY)
                .putHeader("ResourceVersion",  "v4")
                .timeout(13000)
                .as(BodyCodec.json(Flights.class));

        request.send(handler);
    }

    //    private Response createResponseModel(AsyncResult<HttpResponse<FlightList>> handler)
//    {
//        FlightList flightList = handler.result().body();
//        Flight flight = flightList.getFlights().stream().findFirst().orElseThrow(() -> new RuntimeException("flight not found"));
//
//        Response response = new Response();
//        response.setFlightName(flight.getFlightName());
//        response.setTimeToBoard(getDepartureTime(flight));
//        response.setTimeToBeAtSchiphol(response.getTimeToBoard().minusHours(3));
//
//        return response;
//    }

//    private LocalDateTime getDepartureTime(Flight flight)
//    {
//        return LocalDateTime.parse(flight.getScheduleDate() + "T" + flight.getScheduleTime());
//    }

//    private void getTravelTimeByCar(String origin, long arrivalTime, Handler<AsyncResult<HttpResponse<JsonObject>>> handler)
//    {
//        logger.info("Calling google API https://maps.googleapis.com/maps/api/distancematrix/json with origin {}", origin);
//
//        HttpRequest<JsonObject> request = client
//                .getAbs("https://maps.googleapis.com/maps/api/distancematrix/json")
//                .addQueryParam("origins", origin)
//                .addQueryParam("destinations", "Schiphol")
//                .addQueryParam("arrival_time",  String.valueOf(arrivalTime))
//                .timeout(3)
//                .as(BodyCodec.jsonObject());
//
//        request.send(handler);
//    }

//    private void addTimeToLeave(Response response, AsyncResult<HttpResponse<JsonObject>> httpResponseAsyncResult)
//    {
//        HttpResponse<JsonObject> result = httpResponseAsyncResult.result();
//        Integer secondsToTravel = result.body().getJsonArray("rows")
//                .getJsonObject(0)
//                .getJsonArray("elements")
//                .getJsonObject(0)
//                .getJsonObject("duration")
//                .getInteger("value");
//        response.setTimeToLeave(response.getTimeToBeAtSchiphol().minusSeconds(secondsToTravel));
//    }
}