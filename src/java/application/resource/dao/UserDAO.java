package application.resource.dao;

import application.resource.dao.connection.DBConnection;
import application.resource.model.Team;
import application.resource.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Álvaro Santos
 */
public class UserDAO {

    private Connection con = null;
    private static UserDAO instance = null;

    private UserDAO() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static UserDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public void insertWithoutTeam(User e) throws SQLException {

        String query = "INSERT INTO tfg_user (_id_user, _admin, _username, _password, _name, _lastname, _email) VALUES (?,?,?,?,?,?,?);";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, e.getId_user());
            ps.setBoolean(2, e.isAdmin());
            ps.setString(3, e.getUsername());
            ps.setString(4, e.getPassword());
            ps.setString(5, e.getName());
            ps.setString(6, e.getLastname());
            ps.setString(7, e.getEmail());
            ps.executeUpdate();
        }

    }

    public void insertWithTeam(User e, Team team) throws SQLException {
        String query = "INSERT INTO tfg_user (_id_user, _admin, _username, _password, _name, _lastname, _email) VALUES (?,?,?,?,?,?,?);";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, e.getId_user());
            ps.setBoolean(2, e.isAdmin());
            ps.setString(3, e.getUsername());
            ps.setString(4, e.getPassword());
            ps.setString(5, e.getName());
            ps.setString(6, e.getLastname());
            ps.setString(7, e.getEmail());
            ps.executeUpdate();
        }

        String query2 = "INSERT INTO tfg_team (_id_team, _name, _image) VALUES (?,?,?);";
        try (PreparedStatement ps2 = con.prepareStatement(query2)) {
            ps2.setString(1, team.getId_team());
            ps2.setString(2, team.getName());
            ps2.setString(3, team.getImage());
            ps2.executeUpdate();
        }

        String query3 = "INSERT INTO tfg_members_team (_id_team, _id_user) VALUES (?,?);";
        try (PreparedStatement ps3 = con.prepareStatement(query3)) {
            ps3.setString(1, team.getId_team());
            ps3.setString(2, e.getId_user());
            ps3.executeUpdate();

        }
    }

    public ArrayList<User> listAll() throws SQLException {
        String query = "SELECT * FROM tfg_user;";
        ArrayList<User> result;
        try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            result = null;
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(new User(rs.getString("_id_user"), rs.getBoolean("_admin"),
                        rs.getString("_username"), rs.getString("_pasword"), rs.getString("_name"),
                        rs.getString("_lastname"), rs.getString("_email")));
            }
        }
        return result;
    }

    public User listById(String id_user) throws SQLException {
        String query = "SELECT * FROM tfg_user WHERE id_user = ?;";
        User result;
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id_user);
            try (ResultSet rs = ps.executeQuery()) {
                result = null;
                if (rs.next()) {
                    result = new User(rs.getString("_id_user"), rs.getBoolean("_admin"),
                            rs.getString("_username"), rs.getString("_password"), rs.getString("_name"),
                            rs.getString("_lastname"), rs.getString("_email"));
                }
            }
        }
        return result;
    }

    public void remove(User e) throws SQLException {
        remove(e.getId_user());
    }

    public void remove(String id_user) throws SQLException {
        if ("".equals(id_user)) {
            return;
        }
        String query = "DELETE FROM tfg_user WHERE _id_user = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id_user);
            ps.executeUpdate();
        }
    }

    public void update(User e) throws SQLException {
        if ("".equals(e.getId_user())) {
            return;
        }

        String query = "UPDATE tfg_user SET _id_user=?, _admin=?, _username=?, _password=?, _name=?, _lastname=?, _email=? WHERE _id_user=?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, e.getId_user());
            ps.setBoolean(2, e.isAdmin());
            ps.setString(3, e.getUsername());
            ps.setString(4, e.getPassword());
            ps.setString(5, e.getName());
            ps.setString(6, e.getLastname());
            ps.setString(7, e.getEmail());
            ps.setString(8, e.getId_user());
            ps.executeUpdate();
        }
    }
}
