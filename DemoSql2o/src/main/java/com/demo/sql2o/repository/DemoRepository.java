package com.demo.sql2o.repository;

import com.demo.sql2o.model.Student;
import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.math.BigInteger;
import java.util.List;

@Repository
public class DemoRepository
{
    private static final Logger logger = LogManager.getLogger();

    private Sql2o sql2o;
    private ElSql bundle;

    @Autowired
    public DemoRepository(Sql2o sql2o)
    {
        this.sql2o = sql2o;
        this.bundle = ElSql.of(ElSqlConfig.ORACLE, DemoRepository.class);
    }

    public List<Student> findAll()
    {
        String sql = bundle.getSql("GetAllStudent");
        logger.info("GetAllStudent: {}", sql);

        List<Student> result = null;

        try (Connection connection = sql2o.open())
        {
            result = connection.createQuery(sql).executeAndFetch(Student.class);
        }
        catch (Exception e)
        {
            logger.error("Error findAll: {}", e);
        }

        return result;
    }

    public Student findById(long id)
    {
        String sql = bundle.getSql("GetStudentById");
        logger.info("GetStudentById: {}", sql);

        Student result = null;

        try (Connection connection = sql2o.open())
        {
            result = connection.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Student.class);
        }
        catch (Exception e)
        {
            logger.error("Error findById: {}", e);
        }

        return result;
    }

    public void deleteById(long id)
    {
        String sql = bundle.getSql("DeleteStudentById");
        logger.info("DeleteStudentById: {}", sql);

        try (Connection connection = sql2o.open())
        {
            connection.createQuery(sql).addParameter("id", id).executeUpdate();
        }
        catch (Exception e)
        {
            logger.error("Error deleteById: {}", e);
        }
    }

    public BigInteger save(Student student)
    {
        String sql = bundle.getSql("SaveStudent");
        logger.info("SaveStudent: {}", sql);

        BigInteger result = null;

        try (Connection connection = sql2o.open())
        {
            result = (BigInteger) connection.createQuery(sql, true).bind(student).executeUpdate().getKey();
        }
        catch (Exception e)
        {
            logger.error("Error save: {}", e);
        }

        return result;
    }

    public void update(Student student)
    {
        String sql = bundle.getSql("UpdateStudent");
        logger.info("UpdateStudent: {}", sql);

        BigInteger result = null;

        try (Connection connection = sql2o.open())
        {
            connection.createQuery(sql).bind(student).executeUpdate();
        }
        catch (Exception e)
        {
            logger.error("Error update: {}", e);
        }
    }
}