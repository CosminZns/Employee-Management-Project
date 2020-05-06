package members;

import office.Department;
import payment.Salary;

import java.time.LocalDate;
import java.util.Date;

public class Employee extends CompanyMember{

    private Department department;
    private Salary salary;
    private String position;
    private Date hireDate;

    public Employee(Department department, String fname, String sname, int ssn, int monthlySalary, int workingHours, int extraHoursPayment, int vacationDays, String position, Date hireDate)
    {
        super(fname, sname, ssn);
        salary = new Salary(monthlySalary, workingHours, extraHoursPayment, vacationDays);
        this.department = department;
        this.position = position;
        this.hireDate = hireDate;
    }

    public Date getHireDate()
    {
        return hireDate;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public int getSalary(){
        return salary.getMonthlySalary();
    }

    public String getDepartment(){
        return department.getName();
    }



}
