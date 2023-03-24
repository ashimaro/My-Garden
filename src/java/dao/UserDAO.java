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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            System.out.println(e.getMessage());

        } finally {

            con.close();
        }
    }

    public User retrieveUser(String username, String password) throws SQLException {
        ResultSet rs = null;
        User user = null;
        try {
            String mySQLQuery = "select * from tblaccount where Username=? and Password=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();

                user.setEmail(username);
                user.setPassword(password);
                /**
                 * user.setAutoIndex(rs.getInt("autoindex"));
                 *
                 * try { String myQ = "update users set logindatetime=? where
                 * userId=" + rs.getInt("userid");
                 *
                 * PreparedStatement myPS = con.prepareStatement(myQ);
                 *
                 * DateTimeFormatter myFormatObj =
                 * DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
                 * LocalDateTime myDateObj = LocalDateTime.now(); String
                 * formattedDate = myDateObj.format(myFormatObj);
                 *
                 * myPS.setString(1, formattedDate);
                 *
                 * myPS.executeUpdate();
                 *
                 * } catch (SQLException e) {
                 * System.out.println(e.getMessage());
                 *
                 * } finally { try { con.close(); } catch (SQLException ex) {
                 * Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,
                 * null, ex); } }
                 */
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }
            con.close();
        }

        return user;
    }
}
