package io.vertx.data.service;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.data.entity.Todo;

import java.util.List;

public interface TodoService
{
    Completable initData();

    Single<Todo> insert(Todo todo);

    Single<List<Todo>> getAll();

    Maybe<Todo> getCertain(String todoID);

    Maybe<Todo> update(String todoId, Todo newTodo);

    Completable delete(String todoId);

    Completable deleteAll();
}