package payment;

public class WorkDetails {
    private int workingHours;
    private int extraHoursPayment;
    private int vacationDays;
    private int vacationDaysRemaning;

    public WorkDetails(int workingHours, int extraHoursPayment, int vacationDays)
    {
        this.workingHours = workingHours;
        this.extraHoursPayment = extraHoursPayment;
        this.vacationDays = vacationDays;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public int getExtraHoursPayment() {
        return extraHoursPayment;
    }

    public void setExtraHoursPayment(int extraHours) {
        this.extraHoursPayment = extraHours;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public int getVacationDaysRemaning() {
        return vacationDaysRemaning;
    }

    public void setVacationDaysRemaning(int vacationDaysRemaning) {
        this.vacationDaysRemaning = vacationDaysRemaning;
    }

    public void useVacationDays(int usedVacationDays)
    {
        vacationDaysRemaning -= usedVacationDays;
    }

    public void resetVacationDays()
    {
        vacationDaysRemaning = vacationDays;
    }
}
