package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.models.Building;
import guru.springframework.recipeproject.models.Users;
import guru.springframework.recipeproject.repositories.BuildingRepository;
import guru.springframework.recipeproject.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildingPostController {

    @Autowired
    private final BuildingRepository buildingrepository;
    @Autowired
    private final ProjectRepository projectRepository;

    public BuildingPostController(BuildingRepository buildingrepository, ProjectRepository projectRepository) {
        this.buildingrepository = buildingrepository;
        this.projectRepository = projectRepository;
    }


    @GetMapping("/buildings")
    Iterable<Building> all() {
        return buildingrepository.findAll();
    }


    @PostMapping("/buildings")
    Building newBuilding(@RequestBody Building newBuilding) {
        return buildingrepository.save(newBuilding);
    }

    @PostMapping("/projects/{projectCode}/building")
    public Building createBuilding(@PathVariable(value = "projectCode") Integer projectCode,
                                   @RequestBody Building building) {
        return projectRepository.findById(projectCode).map(project -> {
            building.setProject(project);
            return buildingrepository.save(building);
        }).orElseThrow(() -> new RuntimeException("PostId " + projectCode + " not found"));
    }

    @DeleteMapping("/buildings/{buildingCode}")
    void deleteBuilding(@PathVariable Integer buildingCode) {
        buildingrepository.deleteById(buildingCode);
    }

    @PutMapping("/projects/{projectCode}/buildings/{buildingCode}")
    Building updateBuilding(@RequestBody Building newBuilding, @PathVariable Integer buildingCode
            , @PathVariable Integer projectCode) {

        return projectRepository.findById(projectCode).map(project -> {
            newBuilding.setProject(project);
            buildingrepository.save(newBuilding);
            return buildingrepository.findById(buildingCode).map(building -> {
                building.setBuildingCode(newBuilding.getBuildingCode());
                building.setcCode(newBuilding.getcCode());
                building.setZone(newBuilding.getZone());
                building.setType(newBuilding.getType());
                building.setDescription(newBuilding.getDescription());
                return buildingrepository.save(newBuilding);
            }).orElseThrow(() -> new RuntimeException("PostId " + buildingCode + " not found"));
        }).orElseThrow(() -> new RuntimeException("PostId " + projectCode + " not found"));
    }


}