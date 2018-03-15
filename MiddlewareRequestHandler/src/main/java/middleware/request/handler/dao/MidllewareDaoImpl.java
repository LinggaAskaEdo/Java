package middleware.request.handler.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Lingga on 01/03/18.
 */

@Repository
public class MidllewareDaoImpl implements MiddlewareDao
{
    private final Logger log = LoggerFactory.getLogger(MidllewareDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertRequest(String request)
    {
        boolean result = false;

        String insertRequestSql = "INSERT INTO BBW_PULSA.TRANSACTION (USER_ID, OPERATOR_ID, HARGA) VALUES (?)";

        log.debug("insertRequest: {}", insertRequestSql);

        try
        {
            jdbcTemplate.update(insertRequestSql, request);

            result = true;
        }
        catch (Exception e)
        {
            log.error("ERROR when saveTransaction: " + e.getMessage());
        }

        return result;
    }
}