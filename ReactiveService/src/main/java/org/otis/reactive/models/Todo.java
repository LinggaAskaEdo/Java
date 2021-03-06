package org.otis.reactive.models;

import io.vertx.core.json.JsonObject;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Todo
{
    private static final AtomicInteger acc = new AtomicInteger(0); // counter

    private int id;
    private String title;
    private Boolean completed;
    private Integer order;
    private String url;

    public Todo(JsonObject obj)
    {
        TodoConverter.fromJson(obj, this);
    }

    public Todo(String jsonStr)
    {
        TodoConverter.fromJson(new JsonObject(jsonStr), this);
    }

    public Todo(int id, String title, Boolean completed, Integer order, String url)
    {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
        this.url = url;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setIncId()
    {
        this.id = acc.incrementAndGet();
    }

    public static int getIncId()
    {
        return acc.get();
    }

    public static void setIncIdWith(int n)
    {
        acc.set(n);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Boolean isCompleted()
    {
        return getOrElse(completed, false);
    }

    public void setCompleted(Boolean completed)
    {
        this.completed = completed;
    }

    public Integer getOrder()
    {
        return getOrElse(order, 0);
    }

    public void setOrder(Integer order)
    {
        this.order = order;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Todo todo = (Todo) o;

        if (id != todo.id)
            return false;

        if (!title.equals(todo.title))
            return false;

        if (!Objects.equals(completed, todo.completed))
            return false;

        return Objects.equals(order, todo.order);
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);

        return result;
    }

    @Override
    public String toString()
    {
        return "Todo -> {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", order=" + order +
                ", url='" + url + '\'' +
                '}';
    }

    private <T> T getOrElse(T value, T defaultValue)
    {
        return value == null ? defaultValue : value;
    }

    public Todo merge(Todo todo)
    {
        return new Todo(id,
                getOrElse(todo.title, title),
                getOrElse(todo.completed, completed),
                getOrElse(todo.order, order),
                url);
    }
}