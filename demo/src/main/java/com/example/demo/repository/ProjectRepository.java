package com.example.demo.repository;

import com.example.demo.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
