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

        Integer resultId = null;

        if (requestMiddleware.getUuid() != null && requestMiddleware.getRole() != null)
        {
            String role = requestMiddleware.getRole();

            log.debug("role: {}", role);

            resultId = dao.insertRequest(new Gson().toJson(requestMiddleware));

            if (resultId > 0)
            {
                requestMiddleware.setStatus("1");

                String roleDesc = dao.getRoleDesc(role);

                if (roleDesc != null && !roleDesc.isEmpty())
                {
                    requestMiddleware.setMessage(role.toUpperCase() + " " + roleDesc);
                }
                else
                {
                    requestMiddleware.setMessage("~");
                }

                log.info("Success save request !!!");
            }
            else
            {
                requestMiddleware.setStatus("0");
                requestMiddleware.setMessage("Failed to save Request !!!");

                log.info("Fail save request !!!");
            }
        }
        else
        {
            requestMiddleware.setStatus("0");
            requestMiddleware.setMessage("Invalid response");
        }

        log.debug("response: {}", requestMiddleware.toString());

        if (dao.insertResponse(resultId, requestMiddleware))
        {
            log.info("Success save response !!!");
        }
        else
        {
            log.info("Fail save response !!!");
        }

        return new Gson().toJson(requestMiddleware);
    }

    @ExceptionHandler(Exception.class)
    public void handleExceptions(Exception e)
    {
        log.error("Error message: {}", e.getMessage());
    }
}