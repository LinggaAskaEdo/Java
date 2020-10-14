package com.hexagonal.sample.application.service;

import com.hexagonal.sample.application.domain.BankAccount;
import com.hexagonal.sample.application.port.incoming.DepositUseCase;
import com.hexagonal.sample.application.port.incoming.WithdrawUseCase;
import com.hexagonal.sample.application.port.outgoing.LoadAccountPort;
import com.hexagonal.sample.application.port.outgoing.SaveAccountPort;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class BankAccountService implements DepositUseCase, WithdrawUseCase
{
    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    public BankAccountService(LoadAccountPort loadAccountPort, SaveAccountPort saveAccountPort)
    {
        this.loadAccountPort = loadAccountPort;
        this.saveAccountPort = saveAccountPort;
    }

    @Override
    public void deposit(Long id, BigDecimal amount)
    {
        BankAccount account = loadAccountPort.load(id).orElseThrow(NoSuchElementException::new);

        account.deposit(amount);

        saveAccountPort.save(account);
    }

    @Override
    public boolean withdraw(Long id, BigDecimal amount)
    {
        BankAccount account = loadAccountPort.load(id).orElseThrow(NoSuchElementException::new);

        boolean hasWithdrawn = account.withdraw(amount);

        if (hasWithdrawn)
        {
            saveAccountPort.save(account);
        }

        return hasWithdrawn;
    }
}