package newdb;

import members.ShareHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShareHolderManager {
    private static ShareHolderManager shareHolderManager = new ShareHolderManager();

    private ShareHolderManager(){}

    public ShareHolderManager getInstance(){
        return shareHolderManager;
    }

    public void addShareHolder(ShareHolder shareHolder){
        String sql = "insert into shareHolders values(?,?,?,?)";

        try(Connection connection = new ConnectionManager().getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,shareHolder.getfName());
            statement.setString(1,shareHolder.getfName());
            statement.setInt(1,shareHolder.getSocialSecurityNumber());
            statement.setDouble(1,shareHolder.getOwnedPercentage());
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

}
