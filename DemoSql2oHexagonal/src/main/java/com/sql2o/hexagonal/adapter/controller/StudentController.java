package com.sql2o.hexagonal.adapter.controller;

import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;
import com.sql2o.hexagonal.application.model.exception.BadRequestException;
import com.sql2o.hexagonal.application.port.incoming.StudentUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController
{
    private final StudentUseCase studentUseCase;

    public StudentController(StudentUseCase studentUseCase)
    {
        this.studentUseCase = studentUseCase;
    }

    @GetMapping("/students")
    public Response retrieveAllStudents()
    {
        return studentUseCase.retrieveAllStudents();
    }

    @GetMapping("/students/{id}")
    public Response retrieveStudent(@PathVariable long id)
    {
        return studentUseCase.getStudentById(id);
    }

    @DeleteMapping("/students/{id}")
    public Response deleteStudent(@PathVariable long id)
    {
        return studentUseCase.deleteStudentById(id);
    }

    @PostMapping("/students")
    public Response createStudent(@RequestBody Request request)
    {
        if (null != request.getNim() && null != request.getName() && null != request.getPassportNumber())
            return studentUseCase.saveStudent(request);
        else
            throw new BadRequestException();
    }

    @PutMapping("/students/{id}")
    public Response updateStudent(@RequestBody Request request, @PathVariable long id)
    {
        if (null != request.getNim() && null != request.getName() && null != request.getPassportNumber())
            return studentUseCase.updateStudent(request, id);
        else
            throw new BadRequestException();
    }
}