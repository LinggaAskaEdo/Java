package com.sql2o.hexagonal.adapter.controller;

import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;
import com.sql2o.hexagonal.application.model.exception.BadRequestException;
import com.sql2o.hexagonal.application.port.incoming.EmployeeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController
{
    private final EmployeeUseCase employeeUseCase;

    public EmployeeController(EmployeeUseCase employeeUseCase)
    {
        this.employeeUseCase = employeeUseCase;
    }

    @PostMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveEmployee(@RequestBody Request request)
    {
        if (null != request.getEmployeeId() && null != request.getEmployeeName() && null != request.getGender() && 0 != request.getGrade())
        {
            return new ResponseEntity<>(employeeUseCase.saveEmployee(request), HttpStatus.CREATED);
        }
        else
        {
            throw new BadRequestException();
        }
    }

    @GetMapping(value = "/employee/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getEmployeeById(@PathVariable String employeeId)
    {
        return new ResponseEntity<>(employeeUseCase.getEmployeeById(employeeId), HttpStatus.OK);
    }
}