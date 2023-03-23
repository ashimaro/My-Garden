package dao;

/**
 *
 * @author Ashi
 */
import database.DBConnection;
import java.io.IOException;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private final Connection con;

    public UserDAO() throws SQLException, ClassNotFoundException {
        con = DBConnection.getConnection();
    }

    public void save(User user) throws SQLException {
        try {
            String mySQLQuery = "insert into tblaccount(Username, Email, Password) values(?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, user.getUsername());
            myPS.setString(2, user.getEmail());
            myPS.setString(3, user.getPassword());
           
            myPS.executeUpdate();

       } catch (SQLException e) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error saving user to the database", e);
        throw e;
    }
}

}
