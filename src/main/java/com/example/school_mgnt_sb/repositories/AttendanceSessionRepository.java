package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.attendance.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, UUID> {
    Optional<AttendanceSession> findByTeachingAssignment_IdAndDate(UUID teachingAssignmentId, LocalDate date);
}
