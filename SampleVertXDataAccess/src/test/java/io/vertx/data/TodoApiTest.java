package io.vertx.data;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.data.entity.Todo;
import io.vertx.data.verticle.RxTodoVerticle;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class TodoApiTest
{
    private final static int PORT = 8084;
    private Vertx vertx;

    private final Todo todoEx = new Todo(164, "Test case...", false, 22, "http://127.0.0.1:8082/todos/164");
    private final Todo todoUp = new Todo(164, "Test case...Update!", false, 26, "http://127.0.0.1:8082/todos/164");

    @Before
    public void before(TestContext context)
    {
        vertx = Vertx.vertx();
        final DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", PORT));
        // default config
        RxTodoVerticle todoVerticle = new RxTodoVerticle();

        vertx.deployVerticle(todoVerticle, options, context.asyncAssertSuccess());
    }

    @After
    public void after(TestContext context)
    {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test(timeout = 3000L)
    public void testAddWebClient(TestContext context)
    {
        System.out.println("testAddWebClient");

        WebClient client = WebClient.create(vertx);
        Async async = context.async();

        Todo todo = new Todo(164, "Test case...", false, 22, "/164");

        client
                .post(PORT, "localhost", "/todos")
                .putHeader("content-type", "application/json")
                .sendJson(todo, ar -> {
                    context.assertEquals(201, ar.result().statusCode());
                    client.close();
                    async.complete();
                });
    }

    @Test(timeout = 3000L)
    public void testGetWebClient(TestContext context)
    {
        System.out.println("testGetWebClient");

        WebClient client = WebClient.create(vertx);
        Async async = context.async();

        client
                .get(PORT,"localhost", "/todos/164")
                .send(ar -> {
                    context.assertEquals(new Todo(ar.result().bodyAsString()), todoEx);
                    client.close();
                    async.complete();
                });
    }

    @Test(timeout = 3000L)
    public void testUpdateAndDeleteWebClient(TestContext context)
    {
        System.out.println("testUpdateAndDeleteWebClient");

        WebClient client = WebClient.create(vertx);
        Async async = context.async();

        Todo todo = new Todo(164, "Test case...Update!", false, 26, "/164h");

        client
                .patch(PORT,"localhost", "/todos/164")
                .putHeader("content-type", "application/json")
                .sendJson(todo, ar -> {
                    context.assertEquals(new Todo(ar.result().bodyAsString()), todoUp);

                    client
                            .delete(PORT, "localhost", "/todos/164")
                            .send(arDel -> {
                                context.assertEquals(204, arDel.result().statusCode());
                                async.complete();
                            });
                });
    }
}