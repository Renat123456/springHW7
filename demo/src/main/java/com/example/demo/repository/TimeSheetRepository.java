package com.example.demo.repository;

import com.example.demo.model.TimeSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TimeSheetRepository extends JpaRepository<TimeSheetEntity, Long> {
}
