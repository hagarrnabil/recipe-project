package guru.springframework.recipeproject.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;

@Entity
@Table(name="units")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Unit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer UnitNumber;
    private String description;
    private String status;

    private BigDecimal floor;
    private BigDecimal priceAmount;
    private String priceCurr;
    private BigDecimal size;
    private String unitOfMeasurement;
    private String layoutImage;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created_at;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updated_at;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buildingCode", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("building_code")
    private Building building;


    public Unit() {
    }

    public Unit(Integer unitNumber, String description, String status, BigDecimal floor, BigDecimal priceAmount, String priceCurr, BigDecimal size, String unitOfMeasurement, String layoutImage, Timestamp created_at, Timestamp updated_at, Building building) {
        UnitNumber = unitNumber;
        this.description = description;
        this.status = status;
        this.floor = floor;
        this.priceAmount = priceAmount;
        this.priceCurr = priceCurr;
        this.size = size;
        this.unitOfMeasurement = unitOfMeasurement;
        this.layoutImage = layoutImage;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.building = building;
    }

    public Integer getUnitNumber() {
        return UnitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        UnitNumber = unitNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPriceCurr() {
        return priceCurr;
    }

    public void setPriceCurr(String priceCurr) {
        this.priceCurr = priceCurr;
    }

    public BigDecimal getFloor() {
        return floor;
    }

    public void setFloor(BigDecimal floor) {
        this.floor = floor;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getLayoutImage() {
        return layoutImage;
    }

    public void setLayoutImage(String layoutImage) {
        this.layoutImage = layoutImage;
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

    @JsonBackReference
    public Building getBuilding() {
        return building;
    }
    @JsonBackReference
    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "UnitNumber=" + UnitNumber +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", floor=" + floor +
                ", priceAmount=" + priceAmount +
                ", priceCurr='" + priceCurr + '\'' +
                ", size=" + size +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                ", layoutImage='" + layoutImage + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", building=" + building +
                '}';
    }
}
