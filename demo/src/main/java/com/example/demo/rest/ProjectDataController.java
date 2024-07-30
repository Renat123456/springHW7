package com.example.demo.rest;

import com.example.demo.model.ProjectEntity;
import com.example.demo.service.ProjectDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Проекты из базы данных", description = "Апи для работы с проектами хранящимися в базе данных")
@RestController
@RequestMapping("/api/data/projects")
public class ProjectDataController {
    private final ProjectDataService projectDataService;

    public ProjectDataController(ProjectDataService projectDataService) {
        this.projectDataService = projectDataService;
    }

    @Operation(summary = "Все проекты", description = "Получить все существующие проекты")
    @GetMapping("")
    public ResponseEntity<List<ProjectEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(projectDataService.getAll());
    }

    @Operation(summary = "Проект по номеру", description = "Получить конкретный проект")
    @GetMapping("/{id}")
    public ProjectEntity getById(@PathVariable @Parameter(description = "Номер проекта") Long id){
        return projectDataService.getProject(id);
    }

    @PostMapping("")
    public ProjectEntity addById(@RequestBody ProjectEntity projectEntity){
        return projectDataService.addProject(projectEntity);
    }

    @PutMapping("/{id}")
    public ProjectEntity getById(@PathVariable Long id, @RequestBody ProjectEntity projectEntity){
        return projectDataService.upProject(id, projectEntity);
    }

    @DeleteMapping("/{id}")
    public void delById(@PathVariable Long id){
        projectDataService.delProject(id);
    }
}
