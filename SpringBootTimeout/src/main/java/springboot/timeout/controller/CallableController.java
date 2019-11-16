package springboot.timeout.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;
import springboot.timeout.model.Response;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class CallableController
{
    private int TIMEOUT = 3000;

    @GetMapping(value = "/testCallable")
    public Callable<String> getHelloWorld()
    {
        return () ->
        {
            Thread.sleep(ThreadLocalRandom.current().nextInt(10000));

            return "Hello World !!";
        };
    }

    @GetMapping(value = "/testCallable2")
    public Callable<ResponseEntity<String>> getFoobar()
    {
        return () -> {
            try
            {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));

                Response response = new Response("200", "Success !!!");

                return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
            }
            catch (Exception e)
            {
                Response response = new Response("500", "Error !!!");

                return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        };
    }

    @GetMapping("/testWebAsync")
    public WebAsyncTask<String> getWebAsync()
    {
        Callable<String> callable = () -> {
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));

            return "Callable result";
        };

        return new WebAsyncTask<>(TIMEOUT, callable);
    }

//    @GetMapping("/testWebAsync2")
//    public WebAsyncTask<ResponseEntity<String>> getWebAsync2()
//    {
//        return WebAsyncTask<ResponseEntity<String>> (TIMEOUT, () ->
//        {
//            Response response = new Response("200", "Success !!!");
//
//            return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
//        });
//    }
}