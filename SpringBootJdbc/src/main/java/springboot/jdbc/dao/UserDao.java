package springboot.jdbc.dao;

import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import springboot.jdbc.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDao
{
    private static final Logger logger = LogManager.getLogger();

    private ResultSet resultSet = null;

    private DataSource dataSource;
    private ElSql bundle;

    @Autowired
    public UserDao(@Qualifier("dataSource") DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.bundle = ElSql.of(ElSqlConfig.ORACLE, UserDao.class);
    }

    public User getTypeByUserId(String userId)
    {
        String sql = bundle.getSql("GetTypeByUserId");
        logger.info("GetTypeByUserId: {}", sql);

        User user = new User();

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, userId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                user.setId(resultSet.getFloat("ID"));
                user.setType(resultSet.getString("TYPE"));
            }
        }
        catch (Exception e)
        {
            logger.debug("Error when getPhoneNumberByUserId: {}", e.getMessage());
        }
        finally
        {
           close();
        }

        return user;
    }

    public User getLivenessByUserId(String userId)
    {
        String sql = bundle.getSql("GetLivenessByUserId");
        logger.info("GetLivenessByUserId: {}", sql);

        User user = new User();

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, userId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                user.setLivenessUuid(resultSet.getString("LIVENESS_UUID"));
            }
        }
        catch (Exception e)
        {
            logger.debug("Error when getLivenessByUserId: {}", e.getMessage());
        }
        finally
        {
            close();
        }

        return user;
    }

    private void close()
    {
        try
        {
            if (null != resultSet)
            {
                resultSet.close();
            }
        }
        catch (Exception ignored)
        {
            //Do nothing
        }
    }
}