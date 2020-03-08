package com.springboot.vertx.utils;

import com.springboot.vertx.models.Flight;
import io.vertx.core.json.JsonObject;

public class TodoConverter
{
    private TodoConverter()
    {}

    public static void fromJson(JsonObject json, Flight obj)
    {
        if (json.getValue("lastUpdatedAt") instanceof String)
        {
            obj.setLastUpdatedAt((String) json.getValue("lastUpdatedAt"));
        }

        if (json.getValue("actualLandingTime") instanceof String)
        {
            obj.setActualLandingTime((String) json.getValue("actualLandingTime"));
        }

        if (json.getValue("flightName") instanceof String)
        {
            obj.setFlightName((String) json.getValue("flightName"));
        }
    }
}