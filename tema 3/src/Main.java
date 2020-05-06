import newdb.ConnectionManager;
import java.sql.*;

public class Main {

    public static void main(String[] args){
        String sql = "insert into employees values ('andrei','iancu',1990114440019,4000,'IT','Junior Software Developer','2008-11-11 14:30:00')";

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement writeStatement = connection.prepareStatement(sql)) {

            writeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
