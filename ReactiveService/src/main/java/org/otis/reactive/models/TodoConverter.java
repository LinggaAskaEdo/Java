package org.otis.reactive.models;

import io.vertx.core.json.JsonObject;

public class TodoConverter
{
    private TodoConverter()
    {}

    public static void fromJson(JsonObject json, Todo obj)
    {
        if (json.getValue("completed") instanceof Boolean)
        {
            obj.setCompleted((Boolean) json.getValue("completed"));
        }

        if (json.getValue("id") instanceof Number)
        {
            obj.setId(((Number) json.getValue("id")).intValue());
        }

        if (json.getValue("order") instanceof Number)
        {
            obj.setOrder(((Number) json.getValue("order")).intValue());
        }

        if (json.getValue("title") instanceof String)
        {
            obj.setTitle((String) json.getValue("title"));
        }

        if (json.getValue("url") instanceof String)
        {
            obj.setUrl((String) json.getValue("url"));
        }
    }

    public static void toJson(Todo obj, JsonObject json)
    {
        if (obj.isCompleted() != null)
        {
            json.put("completed", obj.isCompleted());
        }
        json.put("id", obj.getId());

        if (obj.getOrder() != null)
        {
            json.put("order", obj.getOrder());
        }

        if (obj.getTitle() != null)
        {
            json.put("title", obj.getTitle());
        }

        if (obj.getUrl() != null)
        {
            json.put("url", obj.getUrl());
        }
    }
}