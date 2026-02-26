package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, UUID> {
    Optional<AcademicYear> findByName(String name);
    Boolean existsByName(String name);
}
