package testng;

import java.util.List;

/**
 * Created by Lingga on 20/02/17.
 */

public interface EmployeeDao
{
    void saveEmployee(Employee employee);

    List<Employee> findAllEmployees();

    void deleteEmployeeBySsn(String ssn);
}