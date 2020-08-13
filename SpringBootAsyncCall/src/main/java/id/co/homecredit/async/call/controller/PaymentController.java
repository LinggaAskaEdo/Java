package id.co.homecredit.async.call.controller;

import id.co.homecredit.async.call.model.Contract;
import id.co.homecredit.async.call.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class PaymentController
{
    private static final Logger logger = LogManager.getLogger();

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/v1/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contract> getAllPayments()
    {
        List<Contract> paymentList = new ArrayList<>();

        try
        {
            CompletableFuture<List<Contract>> contractsPaymentFuture = paymentService.getContractsPayment();
            CompletableFuture<List<Contract>> pendingsPaymentFuture = paymentService.getPendingsPayment();

            for (Contract contractFuture : contractsPaymentFuture.get())
            {
                Contract contract = new Contract();
                contract.setSourceType("CONTRACT");
                contract.setType(contractFuture.getType());
                contract.setContractNumber(contractFuture.getContractNumber());
                contract.setCommodityName(contractFuture.getCommodityName());

                paymentList.add(contract);
            }

            for (Contract pendingFuture : pendingsPaymentFuture.get())
            {
                Contract contract = new Contract();
                contract.setSourceType("PENDING");
                contract.setType(pendingFuture.getType());
                contract.setContractNumber(pendingFuture.getContractNumber());
                contract.setCommodityName(pendingFuture.getCommodityName());

                paymentList.add(contract);
            }

//            paymentList.addAll(contractsPaymentFuture.get());
//            paymentList.addAll(pendingsPaymentFuture.get());
        }
        catch (Exception e)
        {
            logger.error("Error when getAllPayments: ", e);
        }

        return paymentList;
    }
}