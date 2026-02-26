package com.example.school_mgnt_sb.entities.attendance;


import com.example.school_mgnt_sb.common.enums.AttendanceStatus;
import com.example.school_mgnt_sb.entities.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "attendance_records",
        uniqueConstraints = {
                // One record per student per session
                @UniqueConstraint(name = "uq_att_record_session_student", columnNames = {"session_id", "student_id"})
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class AttendanceRecord {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private AttendanceSession session;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AttendanceStatus status = AttendanceStatus.PRESENT;

    @Column(length = 255)
    private String remark;
}
