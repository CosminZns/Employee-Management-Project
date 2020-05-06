package newdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String name = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=pao;username=sa;password=root";
    private static final String username = "sa";
    private static final String password = "root";

    ConnectionManager() {};

    private static ConnectionManager connectionManager = new ConnectionManager();

    public static  ConnectionManager getInstance(){
      return connectionManager;
    }

    public Connection getConnection() {
        try{
            return DriverManager.getConnection(name);
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;
    }

}
