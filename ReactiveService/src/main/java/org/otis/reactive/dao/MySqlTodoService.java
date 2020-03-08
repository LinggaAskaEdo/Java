package org.otis.reactive.dao;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.jdbc.JDBCClient;
import org.otis.reactive.models.Todo;

import java.util.List;
import java.util.stream.Collectors;

public class MySqlTodoService implements TodoService
{
    private final JDBCClient client;

    private static final String SQL_QUERY_ALL = "SELECT * FROM todo";

    public MySqlTodoService(Vertx vertx, JsonObject config)
    {
        this.client = JDBCClient.createShared(vertx, config);
    }

    @Override
    public Completable checkConnection()
    {
        return client.rxGetConnection().ignoreElement();
    }

    @Override
    public Single<List<Todo>> getAll()
    {
        return client.rxQuery(SQL_QUERY_ALL)
                .map(result -> result
                        .getRows()
                        .stream()
                        .map(Todo::new)
                        .collect(Collectors.toList()));
    }
}