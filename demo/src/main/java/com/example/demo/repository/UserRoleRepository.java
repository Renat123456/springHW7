package com.example.demo.repository;

import com.example.demo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(nativeQuery = true, value = "select role_id from user_roles ur where ur.user_id = :id")
    List<Long> findUserRolesByUserId(Long id);
}
