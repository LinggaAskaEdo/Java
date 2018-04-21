package com.hibernate.example.dao;

import com.hibernate.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class HibernateExampleDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUser(String carName)
    {
        Query query = entityManager.createQuery("SELECT u FROM User u JOIN u.cars c WHERE c.carName = :carName");

        query.setParameter("carName", carName);

        return query.getResultList();
    }
}