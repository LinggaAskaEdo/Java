package com.sql2o.hexagonal.application.port.outgoing;

import com.sql2o.hexagonal.application.model.Student;

import java.math.BigInteger;
import java.util.List;

public interface StudentPort
{
    List<Student> findAll();
    Student findById(long id);
    Student findByNumber(String number);
    boolean deleteById(long id);
    BigInteger save(Student student);
    boolean update(Student student);
}