package com.example.demo.service;

import com.example.demo.model.ProjectEntity;
import com.example.demo.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectDataService {
    private final ProjectRepository projectRepository;

    public ProjectDataService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectEntity> getAll(){
        return projectRepository.findAll();
    }

    public ProjectEntity getProject(Long id) {
        return projectRepository.findById(id).get();
    }

    public ProjectEntity addProject(ProjectEntity project) {
        projectRepository.save(project);
        return project;
    }

    public void delProject(Long id) {
        projectRepository.deleteById(id);
    }

    public ProjectEntity upProject(Long id, ProjectEntity project) {
        project.setId(id);
        projectRepository.save(project);
        return project;
    }
}
