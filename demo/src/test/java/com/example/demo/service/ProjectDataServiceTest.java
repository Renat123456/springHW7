package com.example.demo.service;

import com.example.demo.model.ProjectEntity;
import com.example.demo.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test") //подключение апликейшен файла из тестовых ресурсов
@SpringBootTest
//@SpringBootTest(classes = {ProjectRepository.class, ProjectService.class})
class ProjectDataServiceTest {
    //    @MockBean
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectDataService projectService;

    @Test
    void getProject() {
//        doReturn(Optional.empty()).when(projectRepository).findById(2L);
        ProjectEntity project = new ProjectEntity();
        project.setName("проект тест");
        project = projectService.addProject(project);

        ProjectEntity actual = projectService.getProject(project.getId());

        Assertions.assertTrue(projectService.getProject(2L) != null);
        Assertions.assertEquals(actual.getId(), project.getId());
        Assertions.assertEquals(actual.getName(), project.getName());

    }
}