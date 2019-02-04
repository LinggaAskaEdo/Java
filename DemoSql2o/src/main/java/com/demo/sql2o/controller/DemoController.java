package com.demo.sql2o.controller;

import com.demo.sql2o.model.Student;
import com.demo.sql2o.model.StudentNotFoundException;
import com.demo.sql2o.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DemoController
{
    private DemoService service;

    @Autowired
    public DemoController(DemoService service)
    {
        this.service = service;
    }

    @GetMapping("/students")
    public List<Student> retrieveAllStudents()
    {
        return service.getAllStudent();
    }

    @GetMapping("/students/{id}")
    public Student retrieveStudent(@PathVariable long id)
    {
        Optional<Student> student = Optional.ofNullable(service.getStudentById(id));

        if (!student.isPresent())
            throw new StudentNotFoundException("id: " + id);

        return student.get();
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id)
    {
        service.deleteStudentById(id);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody Student student)
    {
        BigInteger savedStudent = service.saveStudent(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id)
    {
        Optional<Student> studentOptional = Optional.ofNullable(service.getStudentById(id));

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId((int) id);

        service.updateStudent(student);

        return ResponseEntity.noContent().build();
    }
}