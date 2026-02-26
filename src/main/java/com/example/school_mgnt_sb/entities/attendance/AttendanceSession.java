package com.example.school_mgnt_sb.entities.attendance;

import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "attendance_sessions",
        uniqueConstraints = {
                // One attendance session per teachingAssignment per date
                @UniqueConstraint(name = "uq_att_session_ta_date", columnNames = {"teaching_assignment_id", "session_date"})
        })
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class AttendanceSession {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "teaching_assignment_id", nullable = false)
    private TeachingAssignment teachingAssignment;

    @Column(name = "session_date", nullable = false)
    private LocalDate date;

    @Column(length = 255)
    private String note;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AttendanceRecord> records = new ArrayList<>();
}
