package guru.springframework.recipeproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.Set;

@Entity
@Table(name="buildings")
public class Building implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "buildingCode")
    private Integer buildingCode;
    private String description;
    private Integer type;
    private Integer zone;
    private Integer cCode;
    @OneToMany(mappedBy = "building")
    private final Set<Unit> units;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projectCode", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("project_code")
    private Project project;



    public Building(Set<Unit> units) {
        this.units = units;
    }

    public Building(Integer buildingCode, String description, Integer type, Integer zone, Integer cCode,
                    Timestamp created_at, Timestamp updated_at, Set<Unit> units, Project project) {
        this.buildingCode = buildingCode;
        this.description = description;
        this.type = type;
        this.zone = zone;
        this.cCode = cCode;
        this.units = units;

        this.project = project;
    }

    public Building() {

        units = null;
    }

    public Integer getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(Integer buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Integer getcCode() {
        return cCode;
    }

    public void setcCode(Integer cCode) {
        this.cCode = cCode;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingCode='" + buildingCode + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", zone=" + zone +
                ", cCode='" + cCode + '\'' +
                ", project=" + project +
                '}';
    }
}