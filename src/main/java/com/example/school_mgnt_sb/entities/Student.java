package com.example.school_mgnt_sb.entities;

import com.example.school_mgnt_sb.entities.assessment.Score;
import com.example.school_mgnt_sb.entities.attendance.AttendanceRecord;
import com.example.school_mgnt_sb.entities.relationship.Enrollment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students", indexes = {
        @Index(name = "idx_students_code", columnList = "student_code")
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private UUID id;

    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, unique = true, length = 30)
    private String studentCode;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @Builder.Default
    private List<Score> scores = new ArrayList<>();


    @OneToMany(mappedBy = "student")
    @Builder.Default
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();

}
