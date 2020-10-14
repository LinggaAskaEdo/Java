package org.o7planning.sbjdbctrans.controller;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.o7planning.sbjdbctrans.exception.NoContentException;
import org.o7planning.sbjdbctrans.model.Response;
import org.o7planning.sbjdbctrans.service.WSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

@RestController
public class WSController
{
    private static final Logger log = LogManager.getLogger(WSController.class);

    private static final String RESULT = "Result";
    private static final String COMPLETED_PROCESSING = "Completed processing request";

    private final WSService service;

    @Autowired
    public WSController(WSService service)
    {
        this.service = service;
    }

    @GetMapping(value = "/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> detailMovie(@RequestParam(value = "titleKey", defaultValue = "tt2911666") String titleKey)
    {
        return service.getDetailMovie(titleKey);
    }

    @GetMapping(value = "/v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> detailAccount(@RequestParam(value = "accountId", defaultValue = "1") Long accountId)
    {
        return service.getDetailAccount(accountId);
    }

    @GetMapping(value = "/v1/timeout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testTimeout() throws InterruptedException
    {
        Thread.sleep(10000);

        Response response = new Response();
        response.setStatus("200");
        response.setMessage("Yuhuuuu !!!");

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    @Async
    @GetMapping(value = "/v1/future", produces = MediaType.APPLICATION_JSON_VALUE)
    public Future<String> callRestApi()
    {
        Response response = new Response();
        response.setStatus("200");
        response.setMessage("Yuhuuuu2 !!!");

        return new AsyncResult<>(new Gson().toJson(response));
    }

    @Async
    @GetMapping(value = "/v1/completableFuture", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<String> findUser() throws InterruptedException
    {
        Response response = new Response();
        response.setStatus("200");
        response.setMessage("Yuhuuuu3 !!!");

        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);

        return CompletableFuture.completedFuture(new Gson().toJson(response));
    }

    private String processRequest()
    {
        log.info("Start processing request");

        try
        {
            Thread.sleep(5000);
        }
        catch (Exception ignored)
        {
            // Do nothing
        }

        log.info(COMPLETED_PROCESSING);

        return generateResponseSuccess(RESULT);
    }

    private ResponseEntity<String> processRequestThrow(String id)
    {
        log.info("Start processing request");

        if (id.equalsIgnoreCase("1"))
        {
            log.info(COMPLETED_PROCESSING);
            return ResponseEntity.status(HttpStatus.OK).body(generateResponseSuccess(RESULT));
        }
        else
        {
            log.info(COMPLETED_PROCESSING);
            throw new NoContentException();
        }
    }

    private String reverseString(String s)
    {
        log.info("Start reversing string");
        String reversed = new StringBuilder(s).reverse().toString();
        log.info("Completed reversing string");

        return generateResponseSuccess(reversed);
    }

    private String generateResponseSuccess(String text)
    {
        return new Gson().toJson(new Response(HttpStatus.OK.toString(), text));
    }

    private String generateResponseTimeout()
    {
        return new Gson().toJson(new Response(HttpStatus.REQUEST_TIMEOUT.toString(), "Request timeout occurred"));
    }

    private String generateResponseBadRequest()
    {
        return new Gson().toJson(new Response(HttpStatus.BAD_REQUEST.toString(), "Parameter invalid"));
    }

    @GetMapping(path = "/asyncDeferred", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<String> getValueAsyncUsingDeferredResult()
    {
        log.info("Request getValueAsyncUsingDeferredResult received");

        DeferredResult<String> deferredResult = new DeferredResult<>(3000L);

        deferredResult.onTimeout(() -> deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(generateResponseTimeout())));

        ForkJoinPool.commonPool().submit(() -> deferredResult.setResult(processRequest()));

        log.info("Servlet thread getValueAsyncUsingDeferredResult released");

        return deferredResult;
    }

    @GetMapping(path = "/asyncDeferred/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<String>> getValueAsyncUsingDeferredResultParam(@PathVariable String id)
    {
        log.info("Request getValueAsyncUsingDeferredResultParam received");

        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>(3000L);
        deferredResult.onTimeout(() -> deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(generateResponseTimeout())));
        deferredResult.onCompletion(() -> log.info("Processing complete"));

        if (id.equalsIgnoreCase("1") || id.equalsIgnoreCase("2"))
        {
            ForkJoinPool.commonPool().submit(() -> {
                log.info("Processing in separate thread");

                try
                {
                    deferredResult.setResult(processRequestThrow(id));
                }
                catch (Exception e)
                {
                    deferredResult.setErrorResult(e);
                }
            });
        }
        else
        {
            deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateResponseBadRequest()));
        }

        log.info("Servlet thread getValueAsyncUsingDeferredResultParam released");

        return deferredResult;
    }

    @GetMapping(path = "/asyncCompletable")
    public CompletableFuture<String> getValueAsyncUsingCompletableFuture()
    {
        log.info("Request getValueAsyncUsingCompletableFuture received");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::processRequest);

        log.info("Servlet thread getValueAsyncUsingCompletableFuture released");

        return completableFuture;
    }

    @GetMapping(path = "/asyncCompletableComposed")
    public CompletableFuture<String> getValueAsyncUsingCompletableFutureComposed()
    {
        return CompletableFuture
                .supplyAsync(this::processRequest)
                .thenApplyAsync(this::reverseString);
    }
}