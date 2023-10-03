package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.models.Building;
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
}