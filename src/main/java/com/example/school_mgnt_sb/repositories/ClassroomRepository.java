package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {
    List<Classroom> findByAcademicYear_Id(UUID academicYearId);
    Boolean existsByName(String name);
}
