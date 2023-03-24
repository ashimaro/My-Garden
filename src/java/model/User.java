package model;

/**Model - Model represents an object or JAVA POJO 
 carrying data. It can also have logic to update 
 controller if its data changes.
 *
 * @author Ashi
 */
public class User implements java.io.Serializable {

    private int autoIndex;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public int getAutoIndex() {
        return autoIndex;
    }

    public void setAutoIndex(int autoIndex) {
        this.autoIndex = autoIndex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
