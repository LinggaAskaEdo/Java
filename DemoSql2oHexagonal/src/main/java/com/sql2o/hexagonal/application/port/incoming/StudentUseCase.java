package com.sql2o.hexagonal.application.port.incoming;

import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;

public interface StudentUseCase
{
    Response retrieveAllStudents();
    Response getStudentById(long id);
    Response deleteStudentById(long id);
    Response saveStudent(Request request);
    Response updateStudent(Request request, long id);
}