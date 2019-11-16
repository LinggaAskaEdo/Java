package id.springboot.sql2o.service;

import com.google.gson.Gson;
import id.springboot.sql2o.dao.DemoDao;
import id.springboot.sql2o.model.Response;
import id.springboot.sql2o.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService
{
    private static final Logger logger = LogManager.getLogger();

    private DemoDao dao;

    @Autowired
    public DemoService(DemoDao dao)
    {
        this.dao = dao;
    }

    public ResponseEntity<String> getUser(String id)
    {
        logger.debug("userId: {}", id);

        Response response = new Response();
        List<User> userList = dao.findUser(id);

        if (!userList.isEmpty())
        {
            response.setStatus("Success");
            response.setMessage("User is exist !!!");
            response.setUsers(userList);
        }
        else
        {
            logger.debug("User is empty !!!");

            response.setStatus("Failed");
            response.setMessage("User not found !!!");
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> updateUser(String cuid, String id)
    {
        logger.debug("cuid: {}, userId: {}", cuid, id);

        Response response = new Response();

        boolean status = dao.updateUser(cuid, id);

        if (status)
        {
            response.setStatus("Success");
            response.setMessage("User updated !!!");
        }
        else
        {
            response.setStatus("Failed");
            response.setMessage("User can't be update !!!");
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }
}