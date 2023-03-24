
package database;

/**
 *
 * @author Ashi
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //define constants
    private static final String URL = "jdbc:mysql://localhost:3306/dbgarden";//connect to the database
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver"; 
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    
    //declare a static variable to hold the connection obj
    private static Connection conn;
    
    //create static method to establish a connection to db and return to Connection object
    public static Connection getConnection() throws ClassNotFoundException{
        try {
            Class.forName(DRIVER_NAME); //load the driver class
            
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // create a connection obj
        } catch (ClassNotFoundException | SQLException e){
            e.getMessage(); // handle any exceptions that occur
        }
        return conn;// return the connection obj
    }
    
}