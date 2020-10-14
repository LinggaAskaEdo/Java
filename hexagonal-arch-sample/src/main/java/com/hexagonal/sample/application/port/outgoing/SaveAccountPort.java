package com.hexagonal.sample.application.port.outgoing;

import com.hexagonal.sample.application.domain.BankAccount;

public interface SaveAccountPort
{
    void save(BankAccount bankAccount);
}