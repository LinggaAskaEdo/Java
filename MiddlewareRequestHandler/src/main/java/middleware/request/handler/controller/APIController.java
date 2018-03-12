package middleware.request.handler.controller;

import com.google.gson.Gson;
import middleware.request.handler.pojo.Middleware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController
{
    private static final Logger log = LoggerFactory.getLogger(APIController.class);

    @RequestMapping(value = "middleware/test", method = RequestMethod.POST)
    String handleRequest(@RequestBody Middleware requestMiddleware)
    {
        log.debug("request: {}", requestMiddleware.toString());

        if (requestMiddleware.getRole() != null)
        {
            String role = requestMiddleware.getRole();

            log.debug("role: {}", role);

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