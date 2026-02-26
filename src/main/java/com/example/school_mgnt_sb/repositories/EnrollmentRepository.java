package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.common.enums.EnrollmentStatus;
import com.example.school_mgnt_sb.entities.relationship.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    Optional<Enrollment> findByStudent_IdAndAcademicYear_Id(UUID studentId, UUID academicYearId);
    boolean existsByStudent_IdAndAcademicYear_Id(UUID studentId, UUID academicYearId);
    List<Enrollment> findByClassroom_Id(UUID classroomId);

    List<Enrollment> findByAcademicYear_IdAndClassroom_IdAndStatusAndStudent_IdIn(UUID academicYearId,
                                                                                  UUID classroomId,
                                                                                  EnrollmentStatus status,
                                                                                  List<UUID> studentIds);
}
