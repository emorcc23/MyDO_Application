package application.resource.model;

import application.resource.dao.TaskDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Álvaro Santos
 */
public class Task {

    private String id_task;
    private String name;
    private String subject;
    private String description;
    private String type;
    private int estimated_time; //In hours
    private int finalized;
    private String id_team;

    public Task() {
    }

    public Task(String name, String subject, String description, String type, int estimated_time, int finalized, String id_team) {
        this.id_task = "tarea_" + UUID.randomUUID().toString();
        this.name = name;
        this.subject = subject;
        this.description = description;
        this.type = type;
        this.estimated_time = estimated_time;
        this.finalized = finalized;
        this.id_team = id_team;
    }

    public Task(String id_task, String name, String subject, String description, String type, int estimated_time, int finalized, String id_team) {
        this.id_task = id_task;
        this.name = name;
        this.subject = subject;
        this.description = description;
        this.type = type;
        this.estimated_time = estimated_time;
        this.finalized = finalized;
        this.id_team = id_team;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(int estimated_time) {
        this.estimated_time = estimated_time;
    }

    public int getFinalized() {
        return finalized;
    }

    public void setFinalized(int finalized) {
        this.finalized = finalized;
    }

    public String getId_team() {
        return id_team;
    }

    public void setId_team(String id_team) {
        this.id_team = id_team;
    }

    public void insert_Task() throws SQLException {
        TaskDAO.getInstance().insert(this);
    }

    public String listAll_Task() throws SQLException {
        String str = "";
        ArrayList<Task> tasks = TaskDAO.getInstance().listAll();
        str += "<tr>";
        for (int i = 0; i < tasks.size(); i++) {
            str += "<td>" + tasks.get(i).getName() + "</td>"
                    + "<td>" + tasks.get(i).getSubject() + "</td>"
                    + "<td>" + tasks.get(i).getDescription() + "</td>"
                    + "<td>" + tasks.get(i).getType() + "</td>"
                    + "<td>" + tasks.get(i).getEstimated_time() + "</td>"
                    + "<td>" + tasks.get(i).getFinalized() + "</td>"
                    + "<td>" + tasks.get(i).getId_team() + "</td>";
        }
        str += "</tr>";
        return str;
    }

    public String listById_Task(String id_task) throws SQLException {
        String str = "";
        Task e = TaskDAO.getInstance().listById(id_task);
        str += "</tr>"
                + "<td>" + e.getSubject() + "</td>"
                + "<td>" + e.getDescription() + "</td>"
                + "<td>" + e.getType() + "</td>"
                + "<td>" + e.getEstimated_time() + "</td>"
                + "<td>" + e.getFinalized() + "</td>"
                + "<td>" + e.getId_team() + "</td>"
                + "</tr>";
        return str;
    }

    public void removeById(String id_task) throws SQLException {
        TaskDAO.getInstance().remove(id_task);
    }

    public void remove() throws SQLException {
        TaskDAO.getInstance().remove(this);
    }

    public void update() throws SQLException {
        TaskDAO.getInstance().update(this);
    }

}
