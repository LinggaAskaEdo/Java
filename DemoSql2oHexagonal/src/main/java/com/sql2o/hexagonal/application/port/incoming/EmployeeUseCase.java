package com.sql2o.hexagonal.application.port.incoming;

import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;

public interface EmployeeUseCase
{
    Response saveEmployee(Request request);
    Response getEmployeeById(String id);
}