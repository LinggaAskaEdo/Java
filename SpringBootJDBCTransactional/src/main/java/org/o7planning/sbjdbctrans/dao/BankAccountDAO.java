package org.o7planning.sbjdbctrans.dao;

import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import org.o7planning.sbjdbctrans.exception.BankTransactionException;
import org.o7planning.sbjdbctrans.mapper.BankAccountMapper;
import org.o7planning.sbjdbctrans.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class BankAccountDAO extends JdbcDaoSupport
{
    private ElSql bundle;

    @Autowired
    public BankAccountDAO(DataSource dataSource)
    {
        this.setDataSource(dataSource);
        this.bundle = ElSql.of(ElSqlConfig.MYSQL, BankAccountDAO.class);
    }

    public List<BankAccountInfo> getBankAccounts()
    {
        System.out.println("GetBankAccounts");

        // Select ba.Id, ba.Full_Name, ba.Balance From Bank_Account ba
        String sql = bundle.getSql("GetBankAccounts");

        Object[] params = new Object[] {};
        BankAccountMapper mapper = new BankAccountMapper();

        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public BankAccountInfo findBankAccount(Long id)
    {
        // Select ba.Id, ba.Full_Name, ba.Balance From Bank_Account ba
        // Where ba.Id = ?
        String sql = bundle.getSql("FindBankAccount");

        Object[] params = new Object[] { id };
        BankAccountMapper mapper = new BankAccountMapper();

        try
        {
            return this.getJdbcTemplate().queryForObject(sql, params, mapper);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    // MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Long id, double amount) throws BankTransactionException
    {
        BankAccountInfo accountInfo = this.findBankAccount(id);

        if (accountInfo == null)
        {
            throw new BankTransactionException("Account not found " + id);
        }

        double newBalance = accountInfo.getBalance() + amount;

        if (accountInfo.getBalance() + amount < 0)
        {
            throw new BankTransactionException("The money in the account '" + id + "' is not enough (" + accountInfo.getBalance() + ")");
        }
        accountInfo.setBalance(newBalance);

        // Update to DB
        String sqlUpdate = bundle.getSql("UpdateBankAccounts");
        this.getJdbcTemplate().update(sqlUpdate, accountInfo.getBalance(), accountInfo.getId());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void insertHistory(Long fromAccountId, Long toAccountId, double amount)
    {
        String sqlInsert = bundle.getSql("InsertHistory");
        this.getJdbcTemplate().update(sqlInsert, fromAccountId, toAccountId, amount);
    }

    // Do not catch BankTransactionException in this method.
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException
    {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
        insertHistory(fromAccountId, toAccountId, amount);
    }
}