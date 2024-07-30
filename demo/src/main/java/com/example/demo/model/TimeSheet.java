package com.example.demo.model;

import java.time.LocalDate;

public class TimeSheet {

    private Long id;
    private Long projectId;
    private Integer minutes;
    private LocalDate createdAt;

    public TimeSheet(Long id, Long projectId, Integer minutes, LocalDate createdAt) {
        this.id = id;
        this.projectId = projectId;
        this.createdAt = createdAt;
        this.minutes = minutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
