package bbw.pulsa.controller;

import bbw.pulsa.dao.PulsaDao;
import bbw.pulsa.model.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lingga on 12/03/18.
 */

@RestController
public class PulsaAPI
{
    private final Logger log = LoggerFactory.getLogger(PulsaAPI.class);

    @Autowired
    private PulsaDao pulsaDao;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    String login(@RequestBody Request request)
    {
        Response response = new Response();

        if (request.getUsername() != null && request.getPassword() != null)
        {
            log.debug("request: {}", request);

            User user = pulsaDao.login(request.getUsername(), request.getPassword());

            if (user != null)
            {
                response.setStatus("1");
                response.setMsg("Login Berhasil !!!");
                response.setUserId(user.getUserId());
            }
            else
            {
                response.setStatus("0");
                response.setMsg("Login Gagal !!!");
            }
        }
        else
        {
            response.setStatus("0");
            response.setMsg("Login Gagal, Username & Password tidak boleh kosong !!!");
        }

        return new Gson().toJson(response);
    }

    @RequestMapping(value = "operator", method = RequestMethod.GET)
    String operator()
    {
        Response response = new Response();
        List<String> operators = pulsaDao.getOperator();

        if (operators != null && operators.size() > 0)
        {
            response.setStatus("1");
            response.setMsg("Data Ditemukan !!!");
            response.setOperators(operators);
        }
        else
        {
            response.setStatus("0");
            response.setMsg("Data Tidak Ditemukan !!!");
        }

        return new Gson().toJson(response);
    }

    @RequestMapping(value = "voucher", method = RequestMethod.POST)
    String voucher(@RequestBody Request request)
    {
        List<Voucher> vouchers = new ArrayList<>();
        Response response = new Response();

        if (request.getOperator() != null)
        {
            vouchers = pulsaDao.getVouchers(request.getOperator());

            if (vouchers != null && vouchers.size() > 0)
            {
                response.setStatus("1");
                response.setMsg("Data Ditemukan !!!");
                response.setVouchers(vouchers);
            }
            else
            {
                response.setStatus("0");
                response.setMsg("Data Tidak Ditemukan !!!");
            }
        }
        else
        {
            response.setStatus("0");
            response.setMsg("Voucher Gagal, Operator tidak boleh kosong !!!");
        }

        log.debug("vouchers: {}", vouchers);

        return new Gson().toJson(response);
    }

    @RequestMapping(value = "transaction", method = RequestMethod.POST)
    String transaction(@RequestBody Request request)
    {
        Response response = new Response();

        if (request.getUsername() != null && request.getOperator() != null && request.getHarga() != null)
        {
            Integer userId = pulsaDao.getUserId(request.getUsername());
            Integer operatorId = pulsaDao.getOperatorId(request.getOperator());
            boolean status = pulsaDao.saveTransaction(userId, operatorId, request.getHarga());

            if (userId != null && userId != 0 && operatorId != null && operatorId != 0 && status)
            {

                response.setStatus("1");
                response.setMsg("Transaksi Berhasil !!!");
            }
            else
            {
                response.setStatus("0");
                response.setMsg("Transaksi Gagal, Invalid User & Operator !!!");
            }
        }
        else
        {
            response.setStatus("0");
            response.setMsg("Transaksi Gagal, User & Operator tidak boleh kosong !!!");
        }

        return new Gson().toJson(response);
    }
}