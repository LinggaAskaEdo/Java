package testng;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Lingga on 20/02/17.
 */

public class Employee
{
    private int id;

    private String name;

    private LocalDate joiningDate;

    private BigDecimal salary;

    private String ssn;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getJoiningDate()
    {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate)
    {
        this.joiningDate = joiningDate;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public String getSsn()
    {
        return ssn;
    }

    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    @Override
    public String toString()
    {
        return "Employee [id=" + id + ", name=" + name + ", joiningDate=" + joiningDate + ", salary=" + salary + ", ssn=" + ssn + "]";
    }
}