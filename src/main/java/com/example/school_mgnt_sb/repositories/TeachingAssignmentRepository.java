package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment, UUID> {
    List<TeachingAssignment> findByClassroom_Id(UUID classroomId);
    List<TeachingAssignment> findByTeacher_Id(UUID teacherId);

    boolean existsByAcademicYear_IdAndClassroom_IdAndSubject_IdAndTeacher_Id(
            UUID academicYearId, UUID classroomId, UUID subjectId, UUID teacherId
    );
}
