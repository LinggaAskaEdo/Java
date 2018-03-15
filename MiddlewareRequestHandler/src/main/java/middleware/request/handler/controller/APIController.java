package middleware.request.handler.controller;

import com.google.gson.Gson;
import middleware.request.handler.dao.MiddlewareDao;
import middleware.request.handler.pojo.Middleware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lingga on 01/03/18.
 */

@RestController
public class APIController
{
    private static final Logger log = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private MiddlewareDao dao;

    @RequestMapping(value = "middleware/test", method = RequestMethod.POST)
    String handleRequest(@RequestBody Middleware requestMiddleware)
    {
        log.debug("request: {}", requestMiddleware.toString());

        if (requestMiddleware.getUuid() != null && requestMiddleware.getRole() != null)
        {
            String role = requestMiddleware.getRole();

            log.debug("role: {}", role);

            //saving request
            if (dao.insertRequest(requestMiddleware.toString()))
            {
                requestMiddleware.setStatus("1");

                switch (role)
                {
                    case "admin" :
                        requestMiddleware.setMessage(role.toUpperCase() + " have all access");
                        break;
                    default :
                        requestMiddleware.setMessage(role.toUpperCase() + " have limited access");
                }
            }
            else
            {
                requestMiddleware.setStatus("0");
                requestMiddleware.setMessage("Failed to save Request !!!");
            }
        }
        else
        {
            requestMiddleware.setStatus("0");
            requestMiddleware.setMessage("Invalid response");
        }

        log.debug("response: {}", requestMiddleware.toString());

        return new Gson().toJson(requestMiddleware);
    }

    @ExceptionHandler(Exception.class)
    public void handleExceptions(Exception e)
    {
        log.error("Error message: {}", e.getMessage());
    }
}