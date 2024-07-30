package com.example.demo.service;

import com.example.demo.model.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private static Long countId = 1L;
    private final List<Project> projects = new ArrayList<>();

    public List<Project> getALL() {
        return projects;
    }

    public Project getProject(Long id) {
        return projects.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public Project addProject(Project project) {
        project.setId(countId++);
        projects.add(project);
        return project;
    }

    public void delProject(Long id) {
        projects.removeIf(t -> t.getId().equals(id));
    }

    public ResponseEntity<Project> upProject(Long id, Project project) {
        Project projectOld = getProject(id);
        if (projectOld != null) {
            projectOld.setName(project.getName());
            return new ResponseEntity<>(projectOld, HttpStatus.OK);
        }
        return new ResponseEntity<>(addProject(project), HttpStatus.CREATED);
    }
}
