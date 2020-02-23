package org.otis.reactive;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.otis.reactive.models.Flight;

@RunWith(VertxUnitRunner.class)
public class MainVerticleTest
{
    private final static int PORT = 8080;
    private Vertx vertx;

    private final Flight response = new Flight("2020-02-23T01:20:00.064+01:00", "2020-02-22T23:58:55.000+01:00", "HV5752");

    @Before
    public void before(TestContext context)
    {
        vertx = Vertx.vertx();
        final DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", PORT));
        // default config
        MainVerticle todoVerticle = new MainVerticle();

        vertx.deployVerticle(todoVerticle, options, context.asyncAssertSuccess());
    }

    @After
    public void after(TestContext context)
    {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test(timeout = 3000L)
    public void testGetFlights(TestContext context)
    {
        System.out.println("testGetFlights");

        WebClient client = WebClient.create(vertx);
        Async async = context.async();

        client
                .get(PORT,"localhost", "/getFlights")
                .send(ar -> {
                    System.out.println("Result: " + ar.result().bodyAsString());
//                    context.assertEquals(new Flight(ar.result().bodyAsString()), response);
                    client.close();
                    async.complete();
                });
    }
}