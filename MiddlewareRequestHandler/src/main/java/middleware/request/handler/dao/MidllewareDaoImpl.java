package middleware.request.handler.dao;

import middleware.request.handler.pojo.Middleware;
import middleware.request.handler.preference.MiddlewarePreference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer insertRequest(String request)
    {
        Integer resultId = 0;

        String insertRequestSql = "INSERT INTO " + MiddlewarePreference.TABLE_REQUEST + "(" + MiddlewarePreference.COLUMN_REQUEST_CONTENT + ") VALUES (:request_content)";

        log.debug("insertRequestSql: {}", insertRequestSql);

        try
        {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameters = new MapSqlParameterSource().addValue("request_content", request);
            namedParameterJdbcTemplate.update(insertRequestSql, parameters, keyHolder);
            resultId =  keyHolder.getKey().intValue();

            log.debug("resultId: {}", resultId);
        }
        catch (Exception e)
        {
            log.error("ERROR when insertRequest: " + e.getMessage());
        }

        return resultId;
    }

    @Override
    public boolean insertResponse(Integer id, Middleware middleware)
    {
        boolean result = false;

        String insertReponseSql = "UPDATE " + MiddlewarePreference.TABLE_REQUEST + " SET " + MiddlewarePreference.COLUMN_REQUEST_UUID + " = ?, " + MiddlewarePreference.COLUMN_REQUEST_STATUS + " = ?, " +
                MiddlewarePreference.COLUMN_REQUEST_ROLE + " = ?, " + MiddlewarePreference.COLUMN_REQUEST_MESSAGE + " = ? WHERE " + MiddlewarePreference.COLUMN_REQUEST_ID + " = ?";

        log.debug("insertReponseSql: {}", insertReponseSql);

        try
        {
            jdbcTemplate.update(insertReponseSql, middleware.getUuid(), middleware.getStatus(), middleware.getRole(), middleware.getMessage(), id);

            result = true;
        }
        catch (Exception e)
        {
            log.error("ERROR when insertRequest: " + e.getMessage());
        }

        return result;
    }

    @Override
    public String getRoleDesc(String role)
    {
        String result = "";

        String getRoleDescSql = "SELECT " + MiddlewarePreference.ROLE_DESC + " FROM " + MiddlewarePreference.TABLE_ROLE + " WHERE " + MiddlewarePreference.ROLE_NAME + " = ?";

        log.debug("getRoleDesc: {}", getRoleDescSql);

        try
        {
            result = jdbcTemplate.queryForObject(getRoleDescSql, new Object[] { role }, String.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when getRoleDesc: " + e.getMessage());
        }

        return result;
    }
}