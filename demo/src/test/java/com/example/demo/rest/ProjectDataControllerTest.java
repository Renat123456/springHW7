package com.example.demo.rest;

import com.example.demo.model.ProjectEntity;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectDataService;
import org.checkerframework.checker.optional.qual.Present;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWebTestClient
class ProjectDataControllerTest {
    @Autowired
    ProjectDataService projectService;

    @Autowired
    ProjectRepository projectRepository;

//    @Autowired
//    WebTestClient webTestClient;

    @LocalServerPort
    private int port;
    private RestClient restClient;

    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
    }

    @Test
    void getByIdNotFound() {
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restClient.get()
                    .uri("/api/data/projects/-2")
                    .retrieve()
                    .toBodilessEntity();
        });
    }

    @Test
    void getById() {
        ProjectEntity project = new ProjectEntity();
        project.setName("тест2");
        ProjectEntity expected = projectService.addProject(project);
        RestClient restClient = RestClient.create("http://localhost:" + port);

//        webTestClient.get()
//                .uri("/api/data/projects/" + project.getId())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(ProjectEntity.class)
//                .value(actual -> {
//                    Assertions.assertEquals(expected.getId(), actual.getId());
//                });

        ResponseEntity<ProjectEntity> actual = restClient.get()
                .uri("/api/data/projects/" + project.getId())
                .retrieve()
                .toEntity(ProjectEntity.class);

        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        ProjectEntity responseBody = actual.getBody();
        Assertions.assertEquals(responseBody.getId(), expected.getId());
    }

    @Test
    void testCreate() {
        ProjectEntity project = new ProjectEntity();
        project.setName("project");

        ResponseEntity<ProjectEntity> response = restClient.post()
                .uri("/api/data/projects")
                .body(project)
                .retrieve()
                .toEntity(ProjectEntity.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ProjectEntity responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertEquals(responseBody.getName(), project.getName());

        Assertions.assertTrue(projectRepository.existsById(responseBody.getId()));
    }

    @Test
    void testDelete(){
        ProjectEntity project = new ProjectEntity();
        project.setName("project");
        project = projectService.addProject(project);

        ResponseEntity<Void> response = restClient.delete()
                .uri("/api/data/projects/" + project.getId())
                .retrieve()
                .toBodilessEntity();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Assertions.assertFalse(projectRepository.existsById(project.getId()));

    }

}