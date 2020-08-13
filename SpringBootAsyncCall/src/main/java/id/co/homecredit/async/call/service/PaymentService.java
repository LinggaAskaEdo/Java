package id.co.homecredit.async.call.service;

import id.co.homecredit.async.call.model.Contract;
import id.co.homecredit.async.call.model.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService
{
    private static final Logger logger = LogManager.getLogger();

    private final RestTemplate restTemplate;

    @Autowired
    public PaymentService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<List<Contract>> getContractsPayment()
    {
        String url = "https://dev.homecredit.co.id/payment-service/v1/payment/contracts";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-authenticated-userid", "{\"cuid\":\"\",\"mobId\":\"\",\"userName\":\"86702211942\"}");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<Data> response = restTemplate.exchange(url, HttpMethod.GET, entity, Data.class);

        logger.debug("Response contractsPayment: {}", response.getBody());

        return CompletableFuture.completedFuture(response.getBody().getContracts());
    }

    @Async
    public CompletableFuture<List<Contract>> getPendingsPayment()
    {
        String url = "https://dev.homecredit.co.id/payment-service/v1/payment/pending-payment";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-authenticated-userid", "{\"cuid\":\"\",\"mobId\":\"\",\"userName\":\"86702211942\"}");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<Data> response = restTemplate.exchange(url, HttpMethod.GET, entity, Data.class);

        logger.debug("Response pendingsPayment: {}", response.getBody());

        return CompletableFuture.completedFuture(response.getBody().getContracts());
    }
}