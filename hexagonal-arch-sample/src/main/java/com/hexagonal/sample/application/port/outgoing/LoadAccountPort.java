package com.hexagonal.sample.application.port.outgoing;

import com.hexagonal.sample.application.domain.BankAccount;

import java.util.Optional;

public interface LoadAccountPort
{
    Optional<BankAccount> load(Long id);
}