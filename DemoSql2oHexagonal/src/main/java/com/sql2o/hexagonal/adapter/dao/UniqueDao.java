package com.sql2o.hexagonal.adapter.dao;

import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import com.sql2o.hexagonal.application.port.outgoing.UniquePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

@Repository
public class UniqueDao implements UniquePort
{
    private static final Logger logger = LogManager.getLogger();

    private final Sql2o sql2o;
    private final ElSql bundle;

    @Autowired
    public UniqueDao(Sql2o sql2o)
    {
        this.sql2o = sql2o;
        this.bundle = ElSql.of(ElSqlConfig.MYSQL, UniqueDao.class);
    }

    @Override
    @Transactional
    public boolean save(String id)
    {
        String sql = bundle.getSql("SaveUniqueId");
        logger.info("SaveUniqueId: {}", sql);

        boolean result = false;

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            query.addParameter("id", id ).executeUpdate();
            result = true;
        }
        catch (Exception e)
        {
            logger.error("Error when save: ", e);
        }

        return result;
    }

    @Override
    public Integer get(String id)
    {
        String sql = bundle.getSql("GetUniqueId");
        logger.info("GetUniqueId: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            return query.addParameter("id", id).executeScalar(Integer.class);
        }
    }
}