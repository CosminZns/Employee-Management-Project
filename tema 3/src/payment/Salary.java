package payment;

public class Salary {
    private int monthlySalary;
    WorkDetails workDetails;

    public Salary(int monthlySalary, int workingHours, int extraHoursPayment, int vacationDays)
    {
        this.monthlySalary = monthlySalary;
        workDetails = new WorkDetails(workingHours,extraHoursPayment,vacationDays);
    }

    public void setWorkDetails(int workingHours, int extraHoursPayment, int vacationDays)
    {
        workDetails = new WorkDetails(workingHours,extraHoursPayment,vacationDays);
    }

    public void setExtraHoursSalary(int extraHours)
    {
        monthlySalary += extraHours * workDetails.getExtraHoursPayment();
    }

    public void setMonthlySalary(int monthlySalary)
    {
        this.monthlySalary = monthlySalary;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }
}
