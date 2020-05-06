package newdb;

import members.Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerManager {
    private static ManagerManager managerManager = new ManagerManager();

    private ManagerManager(){}

    public ManagerManager getInstance(){
        return managerManager;
    }

    public void addManager(Manager manager){
        String sql = "insert into managers values(?,?,?,?,?,?,?)";

        try(Connection connection = new ConnectionManager().getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, manager.getfName());
            statement.setString(2, manager.getsName());
            statement.setInt(3, manager.getSocialSecurityNumber());
            statement.setInt(4, manager.getSalary());
            statement.setString(5, manager.getDepartment());
            statement.setString(6, manager.getPosition());
            statement.setDate(6, (Date) manager.getHireDate());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
