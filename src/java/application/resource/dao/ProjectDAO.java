package application.resource.dao;

import application.resource.dao.connection.DBConnection;
import application.resource.model.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Álvaro Santos
 */
public class ProjectDAO {

    private Connection con = null;
    private static ProjectDAO instance = null;

    private ProjectDAO() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static ProjectDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new ProjectDAO();
        }
        return instance;
    }

    public void insert(Project e) throws SQLException {
        String query = "INSERT INTO tfg_project (_id_project, _name, _status) VALUES (?,?,?);";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, e.getId_project());
            ps.setString(2, e.getName());
            ps.setInt(3, e.getStatus());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {

        }
    }

    public ArrayList<Project> listAll() throws SQLException {
        String query = "SELECT * FROM tfg_project;";
        ArrayList<Project> result;
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            result = null;
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(new Project(rs.getString("_id_project"), rs.getString("_name"), rs.getInt("_status")));
            }
            rs.close();
        }
        return result;
    }

    public Project listById(String id_project) throws SQLException {
        String query = "SELECT * FROM tfg_project WHERE _id_project = ?;";
        Project result;
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id_project);
            try (ResultSet rs = ps.executeQuery()) {
                result = null;
                if (rs.next()) {
                    result = new Project(rs.getString("_id_project"), rs.getString("_name"), rs.getInt("_status"));
                }
            }
        }
        return result;
    }

    public void remove(Project e) throws SQLException {
        remove(e.getId_project());
    }

    public void remove(String id_project) throws SQLException {
        if ("".equals(id_project)) {
            return;
        }
        String query = "DELETE FROM tfg_project WHERE _id_project = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id_project);
            ps.executeUpdate();
        }
    }

    public void update(Project e) throws SQLException {
        if ("".equals(e.getId_project())) {
            return;
        }

        String query = "UPDATE tfg_project SET _id_project=?, _name=?, _status=? WHERE _id_project=?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, e.getId_project());
            ps.setString(2, e.getName());
            ps.setInt(3, e.getStatus());
            ps.setString(4, e.getId_project());
            ps.executeUpdate();
        }
    }
}
