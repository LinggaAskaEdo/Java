package com.hcid.sql2o.dao;

import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class HcidDao
{
    private static final Logger logger = LogManager.getLogger();

    private Sql2o sql2o;
    private ElSql bundle;

    @Autowired
    public HcidDao(Sql2o sql2o)
    {
        this.sql2o = sql2o;
        this.bundle = ElSql.of(ElSqlConfig.ORACLE, HcidDao.class);
    }

    public String getCuidByUserId(String userId)
    {
        String result = null;

        String sql = bundle.getSql("GetCuidByUserId");
        logger.info("getCuidByUserId: {}", sql);

        try (Connection connection = sql2o.open())
        {
            result = connection.createQuery(sql).addParameter("userId", userId).executeAndFetchFirst(String.class);
        }
        catch (Exception e)
        {
            logger.error("Error getCuidByUserId: {}", e);
        }

        return result;
    }
}