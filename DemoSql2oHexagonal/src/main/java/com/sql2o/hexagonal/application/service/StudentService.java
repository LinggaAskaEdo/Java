package com.sql2o.hexagonal.application.service;

import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;
import com.sql2o.hexagonal.application.model.Student;
import com.sql2o.hexagonal.application.model.exception.InternalServerErrorException;
import com.sql2o.hexagonal.application.model.exception.NoContentException;
import com.sql2o.hexagonal.application.model.exception.NotFoundException;
import com.sql2o.hexagonal.application.port.incoming.StudentUseCase;
import com.sql2o.hexagonal.application.port.outgoing.StudentPort;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class StudentService implements StudentUseCase
{
    private final StudentPort studentPort;

    public StudentService(StudentPort studentPort)
    {
        this.studentPort = studentPort;
    }

    @Override
    public Response retrieveAllStudents()
    {
        Optional<List<Student>> students = Optional.ofNullable(studentPort.findAll());

        if (!students.isPresent())
            throw new NoContentException();

        Response response = new Response();
        response.setStudents(students.get());

        return response;
    }

    @Override
    public Response getStudentById(long id)
    {
        Optional<Student> student = Optional.ofNullable(studentPort.findById(id));

        if (!student.isPresent())
            throw new NotFoundException("id: " + id);

        return new Response(student.get());
    }

    @Override
    public Response deleteStudentById(long id)
    {
        Response response = new Response();

        if (studentPort.deleteById(id))
            response.setMessage("Successfully delete: " + id);
        else
            throw new InternalServerErrorException("Failed delete: " + id);

        return response;
    }

    @Override
    public Response saveStudent(Request request)
    {
        Response response = new Response();

        BigInteger savedStudent = studentPort.save(request);

        if (savedStudent.compareTo(BigInteger.ZERO) > 0)
            response.setMessage(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent).toUri().toString());
        else
            throw new InternalServerErrorException("Failed save data");

        return response;
    }

    @Override
    public Response updateStudent(Request request, long id)
    {
        Response response = new Response();

        Optional<Student> studentOptional = Optional.ofNullable(studentPort.findById(id));

        if (!studentOptional.isPresent())
            throw new NotFoundException("id: " + id);

        request.setId(id);

        if (studentPort.update(request))
            response.setMessage("Successfully update: " + id);
        else
            throw new InternalServerErrorException("Failed update: " + id);

        return response;
    }
}