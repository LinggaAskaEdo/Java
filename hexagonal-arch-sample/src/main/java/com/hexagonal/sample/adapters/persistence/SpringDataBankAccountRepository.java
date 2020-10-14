package com.hexagonal.sample.adapters.persistence;

import com.hexagonal.sample.application.domain.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataBankAccountRepository extends MongoRepository<BankAccount, Long>
{}