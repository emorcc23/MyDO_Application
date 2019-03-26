package application.resource.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.resource.dao.connection.DBConnection;
import application.resource.model.Team;

/**
 *
 * @author Álvaro Santos
 */
public class TeamDAO {

    private Connection con = null;
    private static TeamDAO instance = null;

    private TeamDAO() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static TeamDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new TeamDAO();
        }
        return instance;
    }

    public void insert(Team e) throws SQLException {
        try {
            String query = "INSERT INTO tfg_team (_id_team, _name, _image) VALUES (?,?,?);";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, e.getId_team());
                ps.setString(2, e.getName());
                ps.setString(3, e.getImage());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {

        }
    }

    public ArrayList<Team> listAll() throws SQLException {
        String query = "SELECT * FROM tfg_team;";
        ArrayList<Team> result;
        try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            result = null;
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(new Team(rs.getString("_id_team"), rs.getString("_name"), rs.getString("_image")));
            }          }
        return result;
    }

    public Team listById(String id_team) throws SQLException {
        String query = "SELECT * FROM tfg_team WHERE _id_team = ?;";
        Team result;
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id_team);
            try (ResultSet rs = ps.executeQuery()) {
                result = null;
                if (rs.next()) {
                    result = new Team(rs.getString("_id_team"), rs.getString("_name"), rs.getString("_image"));
                }
            }
        }
        return result;
    }

    public void remove(Team e) throws SQLException {
        remove(e.getId_team());
    }

    public void remove(String id_team) throws SQLException {
        if ("".equals(id_team)) {
            return;
        }
        String query = "DELETE FROM tfg_team WHERE _id_team = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id_team);
            ps.executeUpdate();
        }
    }

    public void update(Team e) throws SQLException {
        if ("".equals(e.getId_team())) {
            return;
        }
        String query = "UPDATE tfg_team SET _id_team=?, name=?, image=? WHERE _id_team=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, e.getId_team());
        ps.setString(2, e.getName());
        ps.setString(3, e.getImage());
        ps.setString(4, e.getId_team());
    }

}
