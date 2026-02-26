package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.GradeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface GradeLevelRepository extends JpaRepository<GradeLevel, UUID> {
    Optional<GradeLevelRepository> findByName(String name);
    boolean existsByName(String name);
}
