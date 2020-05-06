package newdb;

import members.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeManager {
    private static EmployeeManager employeeManager = new EmployeeManager();

    private EmployeeManager() {}

    public EmployeeManager getInstance() {
        return employeeManager;
    }

    public void addEmployee(Employee employee){
        String sql = "insert into employees value(?,?,?,?,?,?,?)";

        try(Connection connection = new ConnectionManager().getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, employee.getfName());
                statement.setString(2, employee.getsName());
                statement.setInt(3, employee.getSocialSecurityNumber());
                statement.setInt(4, employee.getSalary());
                statement.setString(5, employee.getDepartment());
                statement.setString(6, employee.getPosition());
                statement.setDate(6, (Date) employee.getHireDate());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


}
