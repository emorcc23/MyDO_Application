package application.resource.model;

import application.resource.dao.ProjectDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Álvaro Santos
 */
public class Project {
    
    private String id_project;
    private String name;
    private int status;
    
    public Project() {}
    
    public Project(String name, int status) {
        this.id_project = "project_" + UUID.randomUUID().toString();
        this.name = name;
        this.status = status;
    }
    
    public Project(String id_project, String name, int status) {
        this.id_project = id_project;
        this.name = name;
        this.status = status;
    }

    public String getId_project() {
        return id_project;
    }

    public void setId_project(String id_project) {
        this.id_project = id_project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void insert_Project() throws SQLException {
        ProjectDAO.getInstance().insert(this);
    }
    
    public String listAll_Project() throws SQLException {
        String str = "";
        ArrayList<Project> projects = ProjectDAO.getInstance().listAll();
        str += "<tr>";
        for(int i = 0; i < projects.size(); i++) {
            str += "<td>"+projects.get(i).getName()+"</td>"
                    + "<td>"+projects.get(i).getStatus()+"</td>";
        }
        str += "</tr>";
        return str;
    } 
    
    public String listById_Project(String id_project) throws SQLException {
        String str = "";
        Project e = ProjectDAO.getInstance().listById(id_project);
        str += "<tr>"
                + "<td>"+e.getName()+"</td>"
                + "<td>"+e.getStatus()+"</td>"
                + "</tr>";
        return str;
    }
    
    public void removeById_Project(String id_project) throws SQLException {
        ProjectDAO.getInstance().remove(id_project);
    }
    
    public void remove_Project() throws SQLException {
        ProjectDAO.getInstance().remove(this);
    }
    
    public void update_Project() throws SQLException {
        ProjectDAO.getInstance().update(this);
    }

}
