package com.sba.test.db.repositories;

import com.sba.test.db.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer>
{
    DoubleStream findById(int personId);

    void deleteById(int personId);

    boolean existsById(int personId);
}