package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.entities.attendance.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, UUID> {
    List<AttendanceRecord> findBySession_Id(UUID sessionId);
    List<AttendanceRecord> findByStudent_Id(UUID studentId);

    Optional<AttendanceRecord> findBySession_IdAndStudent_Id(UUID sessionId, UUID studentId);
}
