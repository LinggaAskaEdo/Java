package com.springboot.vertx.service;

import com.google.gson.Gson;
import com.springboot.vertx.models.Response;
import com.springboot.vertx.models.Todo;
import io.vertx.core.Handler;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.sqlclient.Pool;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.sqlclient.RowSet;
import io.vertx.reactivex.sqlclient.SqlConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService
{
    private static final Logger logger = LogManager.getLogger(DatabaseService.class);

//    private JDBCClient client;
//    private MySQLPool client;

    private static final String SQL_QUERY_ALL = "SELECT * FROM todo";

//    @Autowired
//    public DatabaseService(JDBCClient client)
//    {
//        this.client = client;
//    }

//    @Autowired
//    public DatabaseService(MySQLPool client)
//    {
//        this.client = client;
//    }

    public Handler<RoutingContext> callDatabase(Pool pool)
    {
        List<Todo> todos = new ArrayList<>();

//        client.query(SQL_QUERY_ALL, ar -> {
//            if (ar.succeeded())
//            {
//                if (ar.succeeded())
//                {
//                    List<JsonArray> results = ar.result().getResults();
//
//                    for (JsonArray row : results)
//                    {
//                        todos.add(new Todo(row.getInteger(0), row.getString(1), row.getBoolean(2), row.getInteger(3), row.getString(4)));
//                    }
//                }
//                else
//                {
//                    logger.info("Error when callDatabase");
//                }
//            }

//            if (ar.succeeded())
//            {
//                RowSet<Row> result = ar.result();
//                logger.info("Got {} rows ", result.size());
//
//                for (Row str : result)
//                {
//                    logger.info("str value: {}", str.getValue(0));
//                }
//            }
//            else
//            {
//                logger.info("Failure: {}", ar.cause().getMessage());
//            }
//
//            // Now close the pool
//            client.close();
//        });

//        logger.info("Size: {}", todos.size());

        pool.getConnection(connectionAsyncResult -> {
            if (connectionAsyncResult.failed())
            {
//                return routingContext -> routingContext
//                        .response()
//                        .putHeader("Content-Type", "application/json")
//                        .end(new Gson().toJson(new Response<>("200", "Request was successful", todos)));

                logger.info("Failed to connect: {}", connectionAsyncResult.cause().getMessage());

                return;
            }

            SqlConnection connection = connectionAsyncResult.result();

            // query some data with arguments
            connection.query(SQL_QUERY_ALL, rs -> {
                if (rs.failed())
                {
                    logger.info("Cannot retrieve the data from the database: {}", rs.cause().getMessage());
                    rs.cause().printStackTrace();

                    return;
                }

                for (Row row : rs.result())
                {
                    logger.info("{}", row);

                    todos.add(new Todo(row.getInteger(0), row.getString(1), row.getBoolean(2), row.getInteger(3), row.getString(4)));
                }

                // and close the connection
                connection.close();
            });
        });

        return routingContext -> routingContext
                .response()
                .putHeader("Content-Type", "application/json")
                .end(new Gson().toJson(new Response<>("200", "Request was successful", todos)));

//        if (!todos.isEmpty())
//        {
//            return routingContext -> routingContext
//                    .response()
//                    .putHeader("Content-Type", "application/json")
//                    .end(new Gson().toJson(new Response<>("200", "Request was successful", todos)));
//        }
//        else
//        {
//            return routingContext -> routingContext
//                    .response()
//                    .putHeader("Content-Type", "application/json")
//                    .setStatusCode(500)
//                    .end(new Gson().toJson(new Response<>("500", "Failed get data from database")));
//        }

//        Single<Object> xxx = client.rxQuery(SQL_QUERY_ALL)
//            .map(ar -> ar.getRows().stream()
//                    .map(Todo::new)
//                    .collect(Collectors.toList())
//            );
//
//        xxx.subscribe(result -> System.out.println("Result: " + result), ex -> {
//            //Handle error
//        });
//
//        return routingContext -> routingContext
//                .response()
//                .putHeader("Content-Type", "application/json")
//                .end(new Gson().toJson(new Response<>("200", "Request was successful", todos)));
    }
}