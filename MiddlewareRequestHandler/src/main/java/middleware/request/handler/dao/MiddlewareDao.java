package middleware.request.handler.dao;

import middleware.request.handler.pojo.Middleware;

/**
 * Created by Lingga on 01/03/18.
 */

public interface MiddlewareDao
{
    Integer insertRequest(String request);
    boolean insertResponse(Integer id, Middleware middleware);
    String getRoleDesc(String role);
}