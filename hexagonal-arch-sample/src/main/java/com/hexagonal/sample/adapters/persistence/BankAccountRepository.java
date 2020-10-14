package com.hexagonal.sample.adapters.persistence;

import com.hexagonal.sample.application.domain.BankAccount;
import com.hexagonal.sample.application.port.outgoing.LoadAccountPort;
import com.hexagonal.sample.application.port.outgoing.SaveAccountPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankAccountRepository implements LoadAccountPort, SaveAccountPort
{
    private final SpringDataBankAccountRepository repository;

    public BankAccountRepository(SpringDataBankAccountRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Optional<BankAccount> load(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public void save(BankAccount bankAccount)
    {
        repository.save(bankAccount);
    }
}