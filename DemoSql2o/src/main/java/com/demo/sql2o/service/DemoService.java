package com.demo.sql2o.service;

import com.demo.sql2o.model.Student;
import com.demo.sql2o.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class DemoService
{
    private DemoRepository repository;

    @Autowired
    public DemoService(DemoRepository repository)
    {
        this.repository = repository;
    }

    public List<Student> getAllStudent()
    {
        return repository.findAll();
    }

    public Student getStudentById(long id)
    {
        return repository.findById(id);
    }

    public void deleteStudentById(long id)
    {
        repository.deleteById(id);
    }

    public BigInteger saveStudent(Student student)
    {
        return repository.save(student);
    }

    public void updateStudent(Student student)
    {
        repository.update(student);
    }
}