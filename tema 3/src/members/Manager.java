package members;

import office.Department;
import java.time.LocalDate;
import java.util.Date;


public class Manager extends Employee {


    public Manager(Department department, String fname, String sname, int ssn, int monthlySalary, int workingHours, int extraHoursPayment, int vacationDays, String position, Date hireDate)
    {
            super(department, fname, sname, ssn, monthlySalary, workingHours, extraHoursPayment, vacationDays, position, hireDate);
    }

    @Override
    public String getPosition()
    {
        return this.getPosition() + " manager";
    }

}
