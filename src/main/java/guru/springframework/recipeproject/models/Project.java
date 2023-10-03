package guru.springframework.recipeproject.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectCode")
    private Integer projectCode;
    private String description;
    //    private Date validFrom;
    private String cCode;
    private String phase;
    @OneToMany(mappedBy = "project")
    private final Set<Building> buildings;


    public Project(Integer projectCode, String description, Date validFrom, String cCode, String phase,
                   Timestamp created_at, Timestamp updated_at,
                   Set<Building> buildings) {
        this.projectCode = projectCode;
        this.description = description;
//        this.validFrom = validFrom;
        this.cCode = cCode;
        this.phase = phase;
        this.buildings = buildings;
    }

    public Project() {
        buildings = null;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectCode='" + projectCode + '\'' +
                ", description='" + description + '\'' +
//                ", validFrom=" + validFrom +
                ", cCode='" + cCode + '\'' +
                ", phase='" + phase + '\'' +
                '}';
    }
}