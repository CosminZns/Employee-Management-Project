package office;

import members.Manager;

public class Department {
    private String name;
    private int numberOfEmployees;
    private Manager manager;

    public Department(String name)
    {
        this.name = name;
        numberOfEmployees = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getManager() {
        return manager.getfName() + " " + manager.getsName();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
