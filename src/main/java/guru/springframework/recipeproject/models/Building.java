package guru.springframework.recipeproject.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Building")
public class Building implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "buildingCode")
    private Integer buildingCode;
    private String description;
    private BigDecimal type;
    private BigDecimal zone;
    private String cCode;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created_at;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updated_at;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private Set<Unit> units = new HashSet<Unit>();

    @ManyToOne
    @JoinColumn(name = "projectCode_fk", referencedColumnName = "projectCode")
    private Project project;

    public void addUnit(Unit unit) {
        getUnits().add(unit);
        unit.setBuilding(this);
    }
    public Building() {
    }

    public Building(Integer buildingCode, String description, BigDecimal type, BigDecimal zone, String cCode, Timestamp created_at, Timestamp updated_at, Set<Unit> units, Project project) {
        this.buildingCode = buildingCode;
        this.description = description;
        this.type = type;
        this.zone = zone;
        this.cCode = cCode;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.units = units;
        this.project = project;
    }

    public Integer getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(Integer buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public BigDecimal getZone() {
        return zone;
    }

    public void setZone(BigDecimal zone) {
        this.zone = zone;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
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
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", project=" + project +
                '}';
    }
}
