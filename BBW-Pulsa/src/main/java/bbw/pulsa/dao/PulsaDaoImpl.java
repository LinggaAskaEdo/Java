package bbw.pulsa.dao;

import bbw.pulsa.model.User;
import bbw.pulsa.model.Voucher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lingga on 12/03/18.
 */

@Repository
public class PulsaDaoImpl implements PulsaDao
{
    private final Logger log = LoggerFactory.getLogger(PulsaDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User login(String username, String password)
    {
        User user = null;

        try
        {
            user = (User) jdbcTemplate.queryForObject("SELECT USER_ID, USER_NAME, USER_PASSWORD FROM BBW_PULSA.USER WHERE USER_NAME = ? AND USER_PASSWORD = ? LIMIT 1", new Object[] { username, password }, new BeanPropertyRowMapper(User.class));
        }
        catch (Exception e)
        {
            log.error("ERROR when login: " + e.getMessage());
        }

        return user;
    }

    @Override
    public List<String> getOperator()
    {
        List<String> operators = new ArrayList<>();

        try
        {
            operators = jdbcTemplate.queryForList("SELECT OPERATOR_NAME FROM BBW_PULSA.OPERATOR", String.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when getOperator: " + e.getMessage());
        }

        return operators;
    }

    @Override
    public List<Voucher> getVouchers(String operator)
    {
        List<Voucher> vouchers = new ArrayList<>();

        try
        {
            vouchers = jdbcTemplate.query("SELECT v.VOUCHER_PULSA, v.VOUCHER_HARGA FROM BBW_PULSA.VOUCHER v INNER JOIN BBW_PULSA.OPERATOR o ON v.OPERATOR_ID = o.OPERATOR_ID WHERE o.OPERATOR_NAME = ?",
                    new Object[] { operator }, new BeanPropertyRowMapper(Voucher.class));
        }
        catch (Exception e)
        {
            log.error("ERROR when getVouchers: " + e.getMessage());
        }

        return vouchers;
    }

    @Override
    public Integer getUserId(String username)
    {
        int userId = 0;

        try
        {
            userId = jdbcTemplate.queryForObject("SELECT USER_ID FROM BBW_PULSA.USER WHERE USER_NAME = ? LIMIT 1", new Object[] { username }, Integer.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when getUserId: " + e.getMessage());
        }

        return userId;
    }

    @Override
    public Integer getOperatorId(String operator)
    {
        int operatorId = 0;

        try
        {
            operatorId = jdbcTemplate.queryForObject("SELECT OPERATOR_ID FROM BBW_PULSA.OPERATOR WHERE OPERATOR_NAME = ? LIMIT 1", new Object[] { operator }, Integer.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when getUserId: " + e.getMessage());
        }

        return operatorId;
    }

    @Override
    public boolean saveTransaction(Integer userId, Integer operatorId, Integer harga)
    {
        boolean result = false;

        try
        {
            jdbcTemplate.update("INSERT INTO BBW_PULSA.TRANSACTION (USER_ID, OPERATOR_ID, HARGA) VALUES (?, ?, ?)", userId, operatorId, harga);

            result = true;
        }
        catch (Exception e)
        {
            log.error("ERROR when saveTransaction: " + e.getMessage());
        }

        return result;
    }
}