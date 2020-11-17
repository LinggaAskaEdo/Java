package com.sql2o.hexagonal.application.service;

import com.sql2o.hexagonal.adapter.preference.ConstantPreference;
import com.sql2o.hexagonal.application.model.Employee;
import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;
import com.sql2o.hexagonal.application.model.exception.InternalServerErrorException;
import com.sql2o.hexagonal.application.model.exception.NoContentException;
import com.sql2o.hexagonal.application.port.incoming.EmployeeUseCase;
import com.sql2o.hexagonal.application.port.outgoing.EmployeePort;

public class EmployeeService implements EmployeeUseCase
{
    private final EmployeePort employeePort;

    public EmployeeService(EmployeePort employeePort)
    {
        this.employeePort = employeePort;
    }

    @Override
    public Response saveEmployee(Request request)
    {
        Response response;

        Employee employee = new Employee();
        employee.setEmployeeId(request.getEmployeeId());
        employee.setName(request.getEmployeeName());
        employee.setGender(request.getGender());
        employee.setGrade(request.getGrade());

        boolean status = employeePort.saveEmployee(employee);

        if (status)
        {
            response = new Response(ConstantPreference.RESPONSE_CODE_CREATED, ConstantPreference.RESPONSE_MESSAGE_CREATED);
        }
        else
        {
            throw new InternalServerErrorException();
        }

        return response;
    }

    @Override
    public Response getEmployeeById(String id)
    {
        Response response = new Response();

        Employee employee = employeePort.getEmployeeById(id);

        if (null != employee)
        {
            response.setEmployee(employee);
        }
        else
        {
            throw new NoContentException();
        }

        return response;
    }
}