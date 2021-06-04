package com.demo.graphql.repository;

import com.demo.graphql.model.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long>
{}