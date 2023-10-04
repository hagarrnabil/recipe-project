package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.models.Project;
import guru.springframework.recipeproject.models.Users;
import guru.springframework.recipeproject.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectPostController {


    private final ProjectRepository projectrepository;


    public ProjectPostController(ProjectRepository projectrepository) {
        this.projectrepository = projectrepository;
    }


    @GetMapping("/projects")
    Iterable<Project> all() {
        return projectrepository.findAll();
    }


    @PostMapping("/projects")
    Project newProject(@RequestBody Project newProject) {
        return projectrepository.save(newProject);
    }

    @DeleteMapping("/projects/{projectCode}")
    void deleteProject(@PathVariable Integer projectCode) {
        projectrepository.deleteById(projectCode);
    }

    @PutMapping("/project/{projectCode}")
    Project updateProject(@RequestBody Project newProject, @PathVariable Integer projectCode) {

        return projectrepository.findById(projectCode).map(project -> {
            project.setProjectCode(newProject.getProjectCode());
            project.setDescription(newProject.getDescription());
            project.setcCode(newProject.getcCode());
            project.setPhase(newProject.getPhase());
            return projectrepository.save(newProject);
        }).orElseGet(() -> {
            newProject.setProjectCode(projectCode);
            return projectrepository.save(newProject);
        });
    }


}