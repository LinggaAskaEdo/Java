package com.sql2o.hexagonal.adapter.dao;

import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import com.sql2o.hexagonal.adapter.config.CacheConfiguration;
import com.sql2o.hexagonal.application.model.BankAccount;
import com.sql2o.hexagonal.application.model.BankHistory;
import com.sql2o.hexagonal.application.model.exception.InternalServerErrorException;
import com.sql2o.hexagonal.application.port.outgoing.BankPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class BankDao implements BankPort
{
    private static final Logger logger = LogManager.getLogger();

    private static final String AMOUNT = "amount";
    private static final String FULL_NAME = "fullName";

    private final Sql2o sql2o;
    private final ElSql bundle;

    @Autowired
    public BankDao(Sql2o sql2o, PlatformTransactionManager transactionManager)
    {
        this.sql2o = sql2o;
        this.bundle = ElSql.of(ElSqlConfig.MYSQL, BankDao.class);
    }

    @Override
    @Cacheable(value = CacheConfiguration.BANK_CACHE)
    public List<BankAccount> getAccounts()
    {
        String sql = bundle.getSql("GetAccounts");
        logger.info("GetAccounts: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            return query.addColumnMapping("full_name", FULL_NAME).executeAndFetch(BankAccount.class);
        }
    }

    @Override
    @Cacheable(value = CacheConfiguration.BANK_CACHE, key = "#id", cacheManager = "redisCacheManager")
    public BankAccount findById(long id)
    {
        String sql = bundle.getSql("GetAccountById");
        logger.info("GetAccountById: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            return query.addParameter("id", id).addColumnMapping("full_name", FULL_NAME).executeAndFetchFirst(BankAccount.class);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addBalance(long senderId, long receiverId, Double amount)
    {
        BankAccount bankAccount = findById(senderId);

        if (null == bankAccount)
        {
            throw new InternalServerErrorException("Account not found " + senderId);
        }

        double newBalance = bankAccount.getBalance() + amount;

        if (newBalance < 0)
        {
            throw new InternalServerErrorException("The money in the account '" + senderId + "' is not enough (" + bankAccount.getBalance() + ")");
        }

        bankAccount.setBalance(newBalance);

        String sqlUpdate = bundle.getSql("UpdateBankAccount");
        logger.info("UpdateBankAccount: {}", sqlUpdate);

        String sqlHistory = bundle.getSql("InsertHistory");
        logger.info("InsertHistory: {}", sqlHistory);

        try (Connection connection = sql2o.beginTransaction(); Query queryUpdate = connection.createQuery(sqlUpdate); Query queryHistory = connection.createQuery(sqlHistory))
        {
            queryUpdate.addParameter(AMOUNT, bankAccount.getBalance()).addParameter("id", bankAccount.getId()).executeUpdate();
            queryHistory.bind(new BankHistory(new Timestamp(new Date().getTime()), senderId, receiverId, amount)).executeUpdate();
            connection.commit();
        }
        catch (Exception e)
        {
            logger.error("Error when addBalance: ", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, rollbackFor = InternalServerErrorException.class)
    public void transferBalance(long sourceId, long destId, Double amount)
    {
        addBalance(sourceId, destId, -amount);
        addBalance(destId, sourceId, amount);
    }

    @Override
    @Transactional
    public void topUpBalance(long receiverId, Double amount)
    {
        BankAccount bankAccount = findById(receiverId);

        if (null == bankAccount)
        {
            throw new InternalServerErrorException("Account not found " + receiverId);
        }

        double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);

        String sqlUpdate = bundle.getSql("UpdateBankAccount");
        logger.info("UpdateBankAccount: {}", sqlUpdate);

        String sqlHistory = bundle.getSql("InsertHistory");
        logger.info("InsertHistory: {}", sqlHistory);

        try (Connection connection = sql2o.beginTransaction(); Query queryUpdate = connection.createQuery(sqlUpdate); Query queryHistory = connection.createQuery(sqlHistory))
        {
            queryUpdate.addParameter(AMOUNT, bankAccount.getBalance()).addParameter("id", bankAccount.getId()).executeUpdate();
            queryHistory.bind(new BankHistory(new Timestamp(new Date().getTime()), 0L, receiverId, amount)).executeUpdate();
            connection.commit();
        }
        catch (Exception e)
        {
            logger.error("Error when topUpBalance: ", e);
        }
    }

    @Override
    @Transactional
    public BigInteger createAccount(String fullName, Double amount)
    {
        BigInteger resultId = BigInteger.ZERO;

        String sql = bundle.getSql("InsertAccount");
        logger.info("InsertAccount: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql, true))
        {
            resultId = (BigInteger) query.addParameter(FULL_NAME, fullName).addParameter(AMOUNT, amount).executeUpdate().getKey();
        }
        catch (Exception e)
        {
            logger.error("Error when createAccount: ", e);
        }

        return resultId;
    }
}