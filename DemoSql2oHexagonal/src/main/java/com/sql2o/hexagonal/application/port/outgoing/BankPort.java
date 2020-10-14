package com.sql2o.hexagonal.application.port.outgoing;

import com.sql2o.hexagonal.application.model.BankAccount;

import java.math.BigInteger;
import java.util.List;

public interface BankPort
{
    List<BankAccount> getAccounts();
    BankAccount findById(long id);
    void addBalance(long senderId, long receiverId, Double amount);
    void transferBalance(long sourceId, long destId, Double amount);
    void topUpBalance(long receiverId, Double amount);
    BigInteger createAccount(String fullName, Double amount);
}