package org.rest.vertx.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/calculate")
public class CalculateController
{
    @GET
    @Path("add")
    public int add(@QueryParam("one") int one, @QueryParam("two") int two)
    {
        return one + two;
    }
}