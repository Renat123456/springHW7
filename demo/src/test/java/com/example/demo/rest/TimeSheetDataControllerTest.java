package com.example.demo.rest;

import com.example.demo.model.ProjectEntity;
import com.example.demo.model.TimeSheetEntity;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TimeSheetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeSheetDataControllerTest {
    @Autowired
    TimeSheetRepository timeSheetRepository;

    @LocalServerPort
    private int port;
    private RestClient restClient;

    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
    }

    @Test
    void getById() {
        TimeSheetEntity timeSheet = new TimeSheetEntity();
        timeSheet.setProjectId(1L);
        timeSheet.setMinutes(500);
        timeSheet.setCreatedAt(LocalDate.now());

        TimeSheetEntity expected = timeSheetRepository.save(timeSheet);

        ResponseEntity<TimeSheetEntity> actual = restClient.get()
                .uri("/api/data/timesheets/" + expected.getId())
                .retrieve()
                .toEntity(TimeSheetEntity.class);

        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        TimeSheetEntity responseBody = actual.getBody();
        Assertions.assertEquals(responseBody.getId(), expected.getId());
        Assertions.assertEquals(responseBody.getProjectId(), expected.getProjectId());
        Assertions.assertEquals(responseBody.getMinutes(), expected.getMinutes());
        Assertions.assertEquals(responseBody.getCreatedAt(), expected.getCreatedAt());
    }

    @Test
    void addById() {
        TimeSheetEntity timeSheet = new TimeSheetEntity();
        timeSheet.setProjectId(1L);
        timeSheet.setMinutes(500);
        timeSheet.setCreatedAt(LocalDate.now());

        ResponseEntity<TimeSheetEntity> response = restClient.post()
                .uri("/api/data/timesheets")
                .body(timeSheet)
                .retrieve()
                .toEntity(TimeSheetEntity.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        TimeSheetEntity responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertEquals(responseBody.getProjectId(), timeSheet.getProjectId());
        Assertions.assertEquals(responseBody.getMinutes(), timeSheet.getMinutes());
        Assertions.assertEquals(responseBody.getCreatedAt(), timeSheet.getCreatedAt());
    }

    @Test
    void delById() {
        TimeSheetEntity timeSheet = new TimeSheetEntity();
        timeSheet.setProjectId(1L);
        timeSheet.setMinutes(500);
        timeSheet.setCreatedAt(LocalDate.now());
        timeSheet = timeSheetRepository.save(timeSheet);

        ResponseEntity<Void> response = restClient.delete()
                .uri("/api/data/timesheets/" + timeSheet.getId())
                .retrieve()
                .toBodilessEntity();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertFalse(timeSheetRepository.existsById(timeSheet.getId()));
    }
}