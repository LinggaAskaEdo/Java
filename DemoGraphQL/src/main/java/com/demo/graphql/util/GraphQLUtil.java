package com.demo.graphql.util;

import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class GraphQLUtil
{
    private static final Logger logger = LogManager.getLogger();

    public void getToken(DataFetchingEnvironment env)
    {
        GraphQLContext context = env.getContext();

        Optional<HttpServletRequest> value = context.getHttpServletRequest();

        value.ifPresent(request -> logger.debug("header content: {}", request.getHeader("content-type")));
    }
}