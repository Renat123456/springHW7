package com.example.demo.rest;

import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
//@Secured({"admin", "user"})
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public List<Project> getAll() {
        return projectService.getALL();
    }

    //@Secured({"admin", "user"})
    @GetMapping("/{id}")
    public Optional<Project> getById(@PathVariable Long id) {
        return Optional.ofNullable(projectService.getProject(id));
    }

    @PostMapping("")
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectService.upProject(id, project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.delProject(id);
    }
}
