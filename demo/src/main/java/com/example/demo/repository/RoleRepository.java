package com.example.demo.repository;

import com.example.demo.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleName, Long> {
    @Query(nativeQuery = true, value = "select name from roles ur where ur.id = :id")
    String findNameById(Long id);
}
