package application.resource.model;

import application.resource.dao.TeamDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Álvaro Santos
 */
public class Team {

    private String id_team;
    private String name;
    private String image;

    public Team() {
    }

    public Team(String name, String image) {
        this.id_team = "team_" + UUID.randomUUID().toString();
        this.name = name;
        this.image = image;
    }

    public Team(String id_team, String name, String image) {
        this.id_team = id_team;
        this.name = name;
        this.image = image;
    }

    public String getId_team() {
        return id_team;
    }

    public void setId_team(String id_team) {
        this.id_team = id_team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void insert_Team() throws SQLException {
        TeamDAO.getInstance().insert(this);
    }

    public String listAll_Team() throws SQLException {
        String str = "";
        ArrayList<Team> teams = TeamDAO.getInstance().listAll();
        str += "<tr>";
        for (int i = 0; i < teams.size(); i++) {
            str += "<td>" + teams.get(i).getName() + "</td>"
                    + "<td>" + teams.get(i).getImage() + "</td>";
        }
        str += "</tr>";
        return str;
    }

    public String listById_Team(String id_team) throws SQLException {
        String str = "";
        Team e = TeamDAO.getInstance().listById(id_team);
        str += "<tr>"
                + "<td>"+e.getName()+"</td>"
                + "<td>"+e.getImage()+"</td>"
                + "</tr>";
        return str;
    }

    public void removeById_Team(String id_team) throws SQLException {
        TeamDAO.getInstance().remove(id_team);
    }

    public void remove_Team() throws SQLException {
        TeamDAO.getInstance().remove(this);
    }

    public void update_Team() throws SQLException {
        TeamDAO.getInstance().update(this);
    }

}
