package org.otis.reactive.utils;

import io.vertx.core.json.JsonObject;
import org.otis.reactive.models.Flight;

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