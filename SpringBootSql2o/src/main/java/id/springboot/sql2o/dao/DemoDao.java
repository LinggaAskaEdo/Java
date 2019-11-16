package id.springboot.sql2o.dao;

import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import id.springboot.sql2o.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DemoDao
{
    private static final Logger logger = LogManager.getLogger();

    private Sql2o sql2o;
    private ElSql bundle;

    @Autowired
    public DemoDao(Sql2o sql2o)
    {
        this.sql2o = sql2o;
        this.bundle = ElSql.of(ElSqlConfig.ORACLE, DemoDao.class);
    }

    public List<User> findUser(String userId)
    {
        String sql = bundle.getSql("FindUserByUserId");
        logger.info("FindUserByUserId: {}", sql);

        List<User> result = new ArrayList<>();

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            result = query.addParameter("userIdParam", userId).executeAndFetch(User.class);
        }
        catch (Exception e)
        {
            logger.debug("Failed when findUser: {}", e.getMessage());
        }

        return result;
    }

    public boolean updateUser(String cuid, String userId)
    {
        String sql = bundle.getSql("UpdateCuidByUserId");
        logger.info("UpdateCuidByUserId: {}", sql);

        boolean result = false;

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            query.addParameter("cuidParam", cuid).addParameter("userIdParam", userId).executeUpdate();
            result = true;
        }
        catch (Exception e)
        {
            logger.debug("Failed when updateUser: {}", e.getMessage());
        }

        return result;
    }
}