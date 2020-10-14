package org.o7planning.sbjdbctrans.service;

import org.o7planning.sbjdbctrans.config.CacheConfig;
import org.o7planning.sbjdbctrans.dao.BankAccountDAO;
import org.o7planning.sbjdbctrans.exception.BankTransactionException;
import org.o7planning.sbjdbctrans.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService
{
    private final BankAccountDAO dao;

    @Autowired
    public DBService(BankAccountDAO dao)
    {
        this.dao = dao;
    }

    @Cacheable(CacheConfig.CACHE_ACCOUNTS)
    public List<BankAccountInfo> getBankAccounts()
    {
        return dao.getBankAccounts();
    }

    @CacheEvict(value = CacheConfig.CACHE_ACCOUNTS, allEntries = true)
    public void sendMoney(Long fromAccountId, Long toAccountId, Double amount) throws BankTransactionException
    {
        dao.sendMoney(fromAccountId, toAccountId, amount);
    }
}