package com.sql2o.hexagonal.application.port.outgoing;

import com.sql2o.hexagonal.application.model.Employee;

public interface EmployeePort
{
    boolean saveEmployee(Employee employee);
    Employee getEmployeeById(String id);
}