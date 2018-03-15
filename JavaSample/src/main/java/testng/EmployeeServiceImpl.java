package testng;

import java.util.List;

/**
 * Created by Lingga on 20/02/17.
 */

public class EmployeeServiceImpl implements EmployeeService
{
    private EmployeeDao dao;

    public EmployeeServiceImpl(EmployeeDao dao)
    {
        this.dao = dao;
    }

    @Override
    public void saveEmployee(Employee employee)
    {
        dao.saveEmployee(employee);
    }

    @Override
    public List<Employee> findAllEmployees()
    {
        return dao.findAllEmployees();
    }

    @Override
    public void deleteEmployeeBySsn(String ssn)
    {
        dao.deleteEmployeeBySsn(ssn);
    }
}