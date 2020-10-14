package com.sql2o.hexagonal.adapter.controller;

import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;
import com.sql2o.hexagonal.application.model.exception.BadRequestException;
import com.sql2o.hexagonal.application.port.incoming.BankUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController
{
    private final BankUseCase bankUseCase;

    public BankController(BankUseCase bankUseCase)
    {
        this.bankUseCase = bankUseCase;
    }

    @GetMapping("/bank/check-balances")
    public ResponseEntity<Response> checkBalances()
    {
        return new ResponseEntity<>(bankUseCase.checkBalances(), HttpStatus.OK);
    }

    @GetMapping("/bank/check-balance/{id}")
    public ResponseEntity<Response> checkBalance(@PathVariable long id)
    {
        return new ResponseEntity<>(bankUseCase.checkBalanceById(id), HttpStatus.OK);
    }

    @PostMapping("/bank/transfer-balance")
    public ResponseEntity<Response> transferBalance(@RequestBody Request request)
    {
        if (null != request.getSourceId() && null != request.getDestId() && null != request.getAmount() && request.getAmount() > 0)
            return new ResponseEntity<>(bankUseCase.transferBalance(request), HttpStatus.OK);
        else
            throw new BadRequestException();
    }

    @PutMapping("/bank/deposit-balance")
    public ResponseEntity<Response> depositBalance(@RequestBody Request request)
    {
        if (null != request.getDestId() && null != request.getAmount() && request.getAmount() > 0)
            return new ResponseEntity<>(bankUseCase.depositBalance(request), HttpStatus.OK);
        else
            throw new BadRequestException();
    }

    @PostMapping("/bank/create-account")
    public ResponseEntity<Response> createAccount(@RequestBody Request request)
    {
        if (null != request.getFullName() && null != request.getAmount() && request.getAmount() > 0)
            return new ResponseEntity<>(bankUseCase.createAccount(request), HttpStatus.CREATED);
        else
            throw new BadRequestException();
    }
}