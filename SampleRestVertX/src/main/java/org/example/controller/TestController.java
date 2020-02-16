package org.example.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestController
{
    @GET
    @Path("/echo")
    @Produces(MediaType.TEXT_HTML)
    public String echo()
    {
        return "Hello world!";
    }

    @GET
    @Path("/execute/{param}")
    public String execute(@PathParam("param") String parameter)
    {
        return parameter;
    }
}