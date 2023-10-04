package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.models.Building;
import guru.springframework.recipeproject.models.Unit;
import guru.springframework.recipeproject.models.Users;
import guru.springframework.recipeproject.repositories.BuildingRepository;
import guru.springframework.recipeproject.repositories.ProjectRepository;
import guru.springframework.recipeproject.repositories.UnitRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
public class UnitPostController {


    @Autowired
    private final UnitRepository unitrepository;
    @Autowired
    private final BuildingRepository buildingrepository;
    @Autowired
    private final ProjectRepository projectrepository;

    public UnitPostController(UnitRepository unitrepository, BuildingRepository buildingrepository, ProjectRepository projectrepository) {
        this.unitrepository = unitrepository;
        this.buildingrepository = buildingrepository;
        this.projectrepository = projectrepository;
    }

    @GetMapping("/units")
    Iterable<Unit> all() {
        return unitrepository.findAll();
    }


    @PostMapping("/units")
    Unit newUnit(@RequestBody Unit newUnit) {
        return unitrepository.save(newUnit);
    }

    @PostMapping("/projects/{projectCode}/buildings/{buildingCode}/unit")
    public Unit createUnit(@PathVariable(value = "projectCode") Integer projectCode,
                           @PathVariable(value = "buildingCode") Integer buildingCode,
                           @RequestBody Unit unit) {

        Building building = new Building();

        return projectrepository.findById(projectCode).map(project -> {
            building.setProject(project);
            buildingrepository.save(building);
            return buildingrepository.findById(buildingCode).map(building1 -> {
                unit.setBuilding(building1);
                return unitrepository.save(unit);
            }).orElseThrow(() -> new RuntimeException("PostId " + buildingCode + " not found"));
        }).orElseThrow(() -> new RuntimeException("PostId " + projectCode + " not found"));
    }

    @DeleteMapping("/units/{UnitNumber}")
    void deleteUnit(@PathVariable Integer UnitNumber) {
        unitrepository.deleteById(UnitNumber);
    }

    @PutMapping("/projects/{projectCode}/buildings/{buildingCode}/units/{UnitNumber}")
    Unit updateUnit(@RequestBody Unit newUnit, @PathVariable Integer buildingCode
            , @PathVariable Integer projectCode, @PathVariable Integer UnitNumber) {

        Building newbuilding = new Building();

        return projectrepository.findById(projectCode).map(project -> {
            newbuilding.setProject(project);
            buildingrepository.save(newbuilding);
            return buildingrepository.findById(buildingCode).map(building -> {
                newUnit.setBuilding(building);
                unitrepository.save(newUnit);
                return unitrepository.findById(UnitNumber).map(unit -> {
                    unit.setUnitNumber(newUnit.getUnitNumber());
                    unit.setDescription(newUnit.getDescription());
                    unit.setStatus(newUnit.getStatus());
                    unit.setFloor(newUnit.getFloor());
                    unit.setPriceAmount(newUnit.getPriceAmount());
                    unit.setPriceCurr(newUnit.getPriceCurr());
                    unit.setSize(newUnit.getSize());
                    unit.setUnitOfMeasurement(newUnit.getUnitOfMeasurement());
                    unit.setLayoutImage(newUnit.getLayoutImage());
                    unit.setCreated_at(newUnit.getCreated_at());
                    unit.setUpdated_at(newUnit.getUpdated_at());
                    unit.setBuilding(newUnit.getBuilding());
                    return unitrepository.save(newUnit);
            }).orElseThrow(() -> new RuntimeException("PostId " + UnitNumber + " not found"));
        }).orElseThrow(() -> new RuntimeException("PostId " + buildingCode + " not found"));
}).orElseThrow(() -> new RuntimeException("PostId " + projectCode + " not found"));

    }
}