package application.resource.model;

import application.resource.dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Álvaro Santos
 */
public class User {
    
    private String id_user;
    private boolean admin;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    
    public User() {}
    
    public User(String username, String password, String name, String lastname, String email) {
        this.id_user = "user_"+UUID.randomUUID().toString();
        this.admin = false;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
    
    public User(String id_user, boolean admin, String username, String password, String name, String lastname, String email) {
        this.id_user = id_user;
        this.admin = admin;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void insertWithoutTeam_User() throws SQLException {
        UserDAO.getInstance().insertWithoutTeam(this);
    }
    
    public void insertWithTeam_User(Team e) throws SQLException {
        UserDAO.getInstance().insertWithTeam(this, e);
    }
    
    public String listAll_User() throws SQLException {
        String str = "";
        ArrayList<User> users = UserDAO.getInstance().listAll();
        str += "<tr>";
        for (int i = 0; i < users.size(); i++) {
            
        }
        return "";
    }
    
    public String listById_User(String id_user) throws SQLException {
        return "";
    }
    
    public void removeById_User(String id_user) throws SQLException {
        UserDAO.getInstance().remove(id_user);
    }
    
    public void remove_User() throws SQLException {
        UserDAO.getInstance().remove(this);
    }
    
    public void update_User() throws SQLException {
        UserDAO.getInstance().update(this);
    }

}
