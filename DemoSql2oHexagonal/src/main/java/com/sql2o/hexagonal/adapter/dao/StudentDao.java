package com.sql2o.hexagonal.adapter.dao;

import com.opengamma.elsql.ElSql;
import com.opengamma.elsql.ElSqlConfig;
import com.sql2o.hexagonal.adapter.config.CacheConfiguration;
import com.sql2o.hexagonal.application.model.Student;
import com.sql2o.hexagonal.application.port.outgoing.StudentPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.math.BigInteger;
import java.util.List;

@Repository
public class StudentDao implements StudentPort
{
    private static final Logger logger = LogManager.getLogger();

    private final Sql2o sql2o;
    private final ElSql bundle;

    @Autowired
    public StudentDao(Sql2o sql2o)
    {
        this.sql2o = sql2o;
        this.bundle = ElSql.of(ElSqlConfig.MYSQL, StudentDao.class);
    }

    @Override
    @Cacheable(value = CacheConfiguration.STUDENT_CACHE)
    public List<Student> findAll()
    {
        String sql = bundle.getSql("GetAllStudent");
        logger.info("GetAllStudent: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            return query.executeAndFetch(Student.class);
        }
    }

    @Override
    @Cacheable(value = CacheConfiguration.STUDENT_CACHE, key = "#id")
    public Student findById(long id)
    {
        String sql = bundle.getSql("GetStudentById");
        logger.info("GetStudentById: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            return query.addParameter("id", id).executeAndFetchFirst(Student.class);
        }
    }

    @Override
    @Cacheable(value = CacheConfiguration.STUDENT_CACHE, key = "#number")
    public Student findByNumber(String number)
    {
        String sql = bundle.getSql("GetStudentByNumber");
        logger.info("GetStudentByNumber: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            return query.addParameter("number", number).executeAndFetchFirst(Student.class);
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id)
    {
        boolean result = false;

        String sql = bundle.getSql("DeleteStudentById");
        logger.info("DeleteStudentById: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            query.addParameter("id", id).executeUpdate();
            result = true;
        }
        catch (Exception e)
        {
            logger.error("Error when deleteById: ", e);
        }

        return result;
    }

    @Override
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

    @Override
    @Transactional
    public boolean update(Student student)
    {
        boolean result = false;

        String sql = bundle.getSql("UpdateStudent");
        logger.info("UpdateStudent: {}", sql);

        try (Connection connection = sql2o.open(); Query query = connection.createQuery(sql))
        {
            query.bind(student).executeUpdate();
            result = true;
        }
        catch (Exception e)
        {
            logger.error("Error when update: ", e);
        }

        return result;
    }
}