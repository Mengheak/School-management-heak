package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.assessment.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, UUID> {
    List<Assessment> findByTeachingAssignment_Id(UUID teachingAssignmentId);
}
