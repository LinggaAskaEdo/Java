package com.hexagonal.sample.adapters.web;

import com.hexagonal.sample.application.port.incoming.DepositUseCase;
import com.hexagonal.sample.application.port.incoming.WithdrawUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class BankAccountController
{
    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;

    public BankAccountController(DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase)
    {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
    }

    @PostMapping(value = "/{id}/deposit/{amount}")
    public void deposit(@PathVariable final Long id, @PathVariable final BigDecimal amount)
    {
        depositUseCase.deposit(id, amount);
    }

    @PostMapping(value = "/{id}/withdraw/{amount}")
    public void withdraw(@PathVariable final Long id, @PathVariable final BigDecimal amount)
    {
        withdrawUseCase.withdraw(id, amount);
    }
}