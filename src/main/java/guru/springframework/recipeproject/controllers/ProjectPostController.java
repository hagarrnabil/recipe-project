package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.models.Project;
import guru.springframework.recipeproject.models.Users;
import guru.springframework.recipeproject.repositories.ProjectRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProjectPostController {

    @Autowired
    private final ProjectRepository projectrepository;


    public ProjectPostController(ProjectRepository projectrepository) {
        this.projectrepository = projectrepository;
    }


    @GetMapping("/projects")
    Iterable<Project> all() {
        return projectrepository.findAll();
    }

    @RequestMapping(value = "/projects/{projectCode}", method = RequestMethod.GET)
    public Optional<Project> findByIds(@PathVariable @NotNull Integer projectCode) {

        return projectrepository.findById(projectCode);
    }


    @PostMapping("/projects")
    Project newProject(@RequestBody Project newProject) {
        return projectrepository.save(newProject);
    }

    @DeleteMapping("/projects/{projectCode}")
    void deleteProject(@PathVariable Integer projectCode) {
        projectrepository.deleteById(projectCode);
    }

    @PutMapping("/projects/{projectCode}")
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
