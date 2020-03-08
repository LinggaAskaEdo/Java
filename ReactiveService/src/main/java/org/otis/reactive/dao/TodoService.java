package org.otis.reactive.dao;

import io.reactivex.Completable;
import io.reactivex.Single;
import org.otis.reactive.models.Todo;

import java.util.List;

public interface TodoService
{
    Completable checkConnection();
    Single<List<Todo>> getAll();
}