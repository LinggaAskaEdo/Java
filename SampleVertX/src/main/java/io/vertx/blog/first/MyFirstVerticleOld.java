//package io.vertx.blog.first;
//
//import io.vertx.core.AbstractVerticle;
//import io.vertx.core.Future;
//import io.vertx.core.http.HttpServerResponse;
//import io.vertx.core.json.Json;
//import io.vertx.core.json.JsonObject;
//import io.vertx.ext.web.Router;
//import io.vertx.ext.web.RoutingContext;
//import io.vertx.ext.web.handler.BodyHandler;
//import io.vertx.ext.web.handler.StaticHandler;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class MyFirstVerticleOld extends AbstractVerticle
//{
//    private static final String CONTENT_TYPE = "content-type";
//    private static final String API_WHISKIES = "/api/whiskies/:id";
//    private static final String JSON_CHARSET = "application/json; charset=utf-8";
//
//    // Store our product
//    private Map<Integer, Whisky> products = new LinkedHashMap<>();
//
//    @Override
//    public void start(Future<Void> future)
//    {
//        createSomeData();
//
//        // Create a router object
//        Router router = Router.router(vertx);
//
//        // Bind "/" to our hello message - so we are still compatible
//        router.route("/").handler(routingContext -> {
//            HttpServerResponse response = routingContext.response();
//            response
//                    .putHeader(CONTENT_TYPE, "text/html")
//                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
//        });
//
//        // Serve static resources from the /assets directory
//        router.route("/assets/*").handler(StaticHandler.create("assets"));
//
//        router.route("/api/whiskies*").handler(BodyHandler.create());
//
//        router.get("/api/whiskies").handler(this::getAll);
//
//        router.get(API_WHISKIES).handler(this::getOne);
//
//        router.post("/api/whiskies").handler(this::addOne);
//
//        router.delete(API_WHISKIES).handler(this::deleteOne);
//
//        router.put(API_WHISKIES).handler(this::updateOne);
//
//        vertx
//                .createHttpServer()
//
//                .requestHandler(router::accept)
//
//                .listen(
//                        // Retrieve the port from the configuration,
//                        // default to 8080.
//                        config().getInteger("http.port", 8080),
//                        result ->
//                        {
//                            if (result.succeeded())
//                            {
//                                future.complete();
//                            }
//                            else
//                            {
//                                future.fail(result.cause());
//                            }
//                        }
//                );
//    }
//
//    // Create some product
//    private void createSomeData()
//    {
//        Whisky bowmore = new Whisky("Bowmore 15 Years Laimrig", "Scotland, Islay");
//        products.put(bowmore.getId(), bowmore);
//
//        Whisky talisker = new Whisky("Talisker 57° North", "Scotland, Island");
//        products.put(talisker.getId(), talisker);
//    }
//
//    // Get all product
//    private void getAll(RoutingContext routingContext)
//    {
//        routingContext
//                .response()
//                .setStatusCode(200)
//                .putHeader(CONTENT_TYPE, JSON_CHARSET)
//                .end(Json.encodePrettily(products.values()));
//    }
//
//    // Add one product
//    private void addOne(RoutingContext routingContext)
//    {
//        final Whisky whisky = Json.decodeValue(routingContext.getBodyAsString(), Whisky.class);
//        products.put(whisky.getId(), whisky);
//        routingContext
//                .response()
//                .setStatusCode(201)
//                .putHeader(CONTENT_TYPE, JSON_CHARSET)
//                .end(Json.encodePrettily(whisky));
//    }
//
//    // Delete one product
//    private void deleteOne(RoutingContext routingContext)
//    {
//        String id = routingContext.request().getParam("id");
//
//        if (id == null)
//        {
//            routingContext.response().setStatusCode(400).end();
//        }
//        else
//        {
//            Integer idAsInteger = Integer.parseInt(id);
//            products.remove(idAsInteger);
//        }
//
//        routingContext
//                .response()
//                .setStatusCode(204)
//                .end();
//    }
//
//    // Get one product
//    private void getOne(RoutingContext routingContext)
//    {
//        final String id = routingContext.request().getParam("id");
//
//        if (id == null)
//        {
//            routingContext
//                    .response()
//                    .setStatusCode(400)
//                    .end();
//        }
//        else
//        {
//            final Integer idAsInteger = Integer.parseInt(id);
//            Whisky whisky = products.get(idAsInteger);
//
//            if (whisky == null)
//            {
//                routingContext.response().setStatusCode(404).end();
//            }
//            else
//            {
//                routingContext
//                        .response()
//                        .setStatusCode(200)
//                        .putHeader(CONTENT_TYPE, JSON_CHARSET)
//                        .end(Json.encodePrettily(whisky));
//            }
//        }
//    }
//
//    // Update one product
//    private void updateOne(RoutingContext routingContext)
//    {
//        final String id = routingContext.request().getParam("id");
//        JsonObject json = routingContext.getBodyAsJson();
//
//        if (id == null || json == null)
//        {
//            routingContext
//                    .response()
//                    .setStatusCode(400)
//                    .end();
//        }
//        else
//        {
//            final Integer idAsInteger = Integer.parseInt(id);
//            Whisky whisky = products.get(idAsInteger);
//
//            if (whisky == null)
//            {
//                routingContext
//                        .response()
//                        .setStatusCode(404)
//                        .end();
//            }
//            else
//            {
//                whisky.setName(json.getString("name"));
//                whisky.setOrigin(json.getString("origin"));
//                routingContext
//                        .response()
//                        .putHeader(CONTENT_TYPE, JSON_CHARSET)
//                        .end(Json.encodePrettily(whisky));
//            }
//        }
//    }
//}