package testng;

import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Lingga on 20/02/17.
 */
public class EmployeeServiceImplTest
{
    @Mock
    private EmployeeDao dao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Spy
    private List<Employee> employees = new ArrayList<>();

    @Captor
    private ArgumentCaptor<Employee> captor;

    @BeforeClass
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        employees = getEmployeeList();
    }

    private List<Employee> getEmployeeList()
    {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("Axel");
        e1.setSsn("11111");

        Employee e2 = new Employee();
        e1.setId(2);
        e2.setName("Jeremy");
        e2.setSsn("11112");

        employees.add(e1);
        employees.add(e2);
        return employees;
    }

    /*
     * Scenario for Successful [error-free] data persistence
     * Void method stubbing example
     */
    @Test
    public void testSaveEmployee() throws Exception
    {
        /*
         * Instruct mockito to do nothing when dao.saveEmployee will be called.
         */
        doNothing().when(dao).saveEmployee(any(Employee.class));
        employeeService.saveEmployee(employees.get(0));

        /*
         * Verify that dao.saveEmployee was indeed called one time.
         */
        verify(dao, times(1)).saveEmployee(captor.capture());

        /*
         * Assert that dao.saveEmployee was called with a particular Employee, assert employee details
         */
        Assert.assertEquals(captor.getValue().getName(), "Axel");

        Assert.assertEquals(2, employees.size());
        verify(employees, times(2)).add(any(Employee.class));
    }

    /*
     * Scenario for Failed data persistence (due to existing user)
     * Void method stubbing example
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void saveExistingEmployee()
    {
        /*
         * Instruct mockito to throw an exception when dao.saveEmployee will be called.
         */
        doThrow(RuntimeException.class).when(dao).saveEmployee(employees.get(0));
        employeeService.saveEmployee(any(Employee.class));
    }

    /*
     * Scenario for Successful data retrieval.
     */
    @Test
    public void findAllEmployees()
    {
        /*
         * Instruct mockito to return pre-populated employees list whenever dao.findAllEmployees will be called.
         */
        when(dao.findAllEmployees()).thenReturn(employees);
        Assert.assertEquals(employeeService.findAllEmployees(), employees);
        verify(dao, times(1)).findAllEmployees();
    }

    /*
     * Scenario for Successful data deletion
     * Void method stubbing example
     */
    @Test
    public void deleteEmployeeBySsn()
    {
        /*
         * Instruct mockito to do nothing when dao.deleteEmployeeBySsn will be called.
         */
        doNothing().when(dao).deleteEmployeeBySsn(anyString());
        employeeService.deleteEmployeeBySsn(anyString());
        verify(dao, times(1)).deleteEmployeeBySsn(anyString());
    }

    /*
     * Scenario for Failed data deletion (due to no employee found with given ssn)
     * Void method stubbing example
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void deleteEmployeeBySsnNotExist()
    {
        /*
         * Instruct mockito to throw an exception when dao.deleteEmployeeBySsn will be called.
         *
         */
        doThrow(RuntimeException.class).when(dao).deleteEmployeeBySsn(anyString());
        employeeService.deleteEmployeeBySsn("XXXX");
        verify(dao, atLeastOnce()).deleteEmployeeBySsn(anyString());
    }

    /*
     * Same as above test case, demonstrates 'doAnswer().when' pattern
     * Void method stubbing example
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void deleteEmployeeBySsnNotExistAgain()
    {
        /*
         * Alternate way to Instruct mockito to throw an exception when dao.deleteEmployeeBySsn will be called.
         */
        doAnswer(invocation ->
        {
            Object[] args = invocation.getArguments();
            String arg = (String) args[0];
            if (arg.equals("UNKNOWN_SSN"))
            {
                throw new RuntimeException("Item not present");
            }
            return null;
        }).when(dao).deleteEmployeeBySsn(anyString());
        employeeService.deleteEmployeeBySsn("UNKNOWN_SSN");
        verify(dao, atLeastOnce()).deleteEmployeeBySsn(anyString());
    }
}