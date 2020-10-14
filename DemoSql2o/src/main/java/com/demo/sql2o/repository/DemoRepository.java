package com.demo.sql2o.repository;

import com.demo.sql2o.model.Student;
import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.math.BigInteger;
import java.util.List;

@Repository
public class DemoRepository
{
    private static final Logger logger = LogManager.getLogger();

    private final Sql2o sql2o;
    private final ElSql bundle;

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

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
           return query.executeAndFetch(Student.class);
        }
    }

    public Student findById(long id)
    {
        String sql = bundle.getSql("GetStudentById");
        logger.info("GetStudentById: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            return query.addParameter("id", id).executeAndFetchFirst(Student.class);
        }
    }

    @Transactional
    public void deleteById(long id)
    {
        String sql = bundle.getSql("DeleteStudentById");
        logger.info("DeleteStudentById: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            query.addParameter("id", id).executeUpdate();
        }
        catch (Exception e)
        {
            logger.error("Error when deleteById: ", e);
        }
    }

    @Transactional
    public BigInteger save(Student student)
    {
        String sql = bundle.getSql("SaveStudent");
        logger.info("SaveStudent: {}", sql);

        BigInteger result = null;

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql, true))
        {
            result = (BigInteger) query.bind(student).executeUpdate().getKey();
        }
        catch (Exception e)
        {
            logger.error("Error when save: ", e);
        }

        return result;
    }

    @Transactional
    public void update(Student student)
    {
        String sql = bundle.getSql("UpdateStudent");
        logger.info("UpdateStudent: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            query.bind(student).executeUpdate();
        }
        catch (Exception e)
        {
            logger.error("Error when update: ", e);
        }
    }
}