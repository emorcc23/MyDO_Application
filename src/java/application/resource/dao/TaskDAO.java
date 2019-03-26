package application.resource.dao;

import application.resource.dao.connection.DBConnection;
import application.resource.model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Álvaro Santos
 */
public class TaskDAO {

    private Connection con = null;
    private static TaskDAO instance = null;

    private TaskDAO() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static TaskDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new TaskDAO();
        }
        return instance;
    }

    public void insert(Task e) throws SQLException {
        try {
            String query = "INSERT INTO tfg_task (_id_task, _name, _subject, _description, _type, _estimated_time, _finalized, _id_team) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, e.getId_task());
            ps.setString(2, e.getName());
            ps.setString(3, e.getSubject());
            ps.setString(4, e.getDescription());
            ps.setString(5, e.getType());
            ps.setInt(6, e.getEstimated_time());
            ps.setInt(7, e.getFinalized());
            ps.setString(8, e.getId_team());
        } catch (SQLException ex) {

        }
    }

    public ArrayList<Task> listAll() throws SQLException {
        String query = "SELECT * FROM tfg_task;";
        ArrayList<Task> result;
        try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            result = null;
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(new Task(rs.getString("_id_task"), rs.getString("_name"), rs.getString("_subject"), rs.getString("_description"), rs.getString("_type"), rs.getInt("_estimated_time"), rs.getInt("_finalized"), rs.getString("_id_team")));
            }          }
        return result;
    }

    public Task listById(String id_task) throws SQLException {
        String query = "SELECT * FROM tfg_task WHERE _id_task = ?;";
        Task result;
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id_task);
            try (ResultSet rs = ps.executeQuery()) {
                result = null;
                if (rs.next()) {
                    result = new Task(rs.getString("_id_task"), rs.getString("_name"), rs.getString("_subject"), rs.getString("_description"), rs.getString("_type"), rs.getInt("_estimated_time"), rs.getInt("_finalized"), rs.getString("_id_team"));
                }
            }
        }
        return result;
    }

    public void remove(Task e) throws SQLException {
        remove(e.getId_task());
    }

    public void remove(String id_task) throws SQLException {
        if ("".equals(id_task)) {
            return;
        }
        String query = "DELETE FROM tfg_task WHERE _id_task = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id_task);
            ps.executeUpdate();
        }
    }

    public void update(Task e) throws SQLException {
        if ("".equals(e.getId_task())) {
            return;
        }

        String query = "UPDATE tfg_task SET _id_task=?, _name=?, _subject=?, _description=?, _type=?, _estimated_time=?, _finalized=?, _id_team=? WHERE _id_task=?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, e.getId_task());
            ps.setString(2, e.getName());
            ps.setString(3, e.getSubject());
            ps.setString(4, e.getDescription());
            ps.setString(5, e.getType());
            ps.setInt(6, e.getEstimated_time());
            ps.setInt(7, e.getFinalized());
            ps.setString(8, e.getId_team());
            ps.setString(9, e.getId_task());
            ps.executeUpdate();
        }
    }
    
    public int proyectIsCreatedOrNot(String subject) throws SQLException {
        int result = 0;
        String query = "SELECT COUNT('_subject') FROM tfg_task WHERE subject = '" + subject + "';";
        try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        return result;
    }
}
