package bbw.pulsa.controller;

import bbw.pulsa.dao.PulsaDao;
import bbw.pulsa.model.Request;
import bbw.pulsa.model.Response;
import bbw.pulsa.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lingga on 12/03/18.
 */

@RestController
public class PulsaAPI
{
    @Autowired
    private PulsaDao pulsaDao;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    String calculatePosition(@RequestBody Request request)
    {
        Response response = new Response();

        if (request.getUsername() != null && request.getPassword() != null)
        {
            System.out.println("request: " + request);

            User user = pulsaDao.login(request.getUsername(), request.getPassword());

            if (user != null)
            {
                response.setStatus("1");
                response.setMsg("Login Berhasil !!!");
                response.setUserId(user.getUserId());
            }
        }
        else
        {
            response.setStatus("0");
            response.setMsg("Login Gagal !!!");
        }

        return new Gson().toJson(response);
    }
}