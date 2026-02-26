package com.example.school_mgnt_sb.entities.relationship;

import com.example.school_mgnt_sb.common.enums.EnrollmentStatus;
import com.example.school_mgnt_sb.entities.AcademicYear;
import com.example.school_mgnt_sb.entities.Classroom;
import com.example.school_mgnt_sb.entities.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "enrollments",uniqueConstraints = {
    @UniqueConstraint(name = "uq_enrollment_student_year", columnNames = {"student_id, academic_year_id"})

},
indexes = {
        @Index(name = "idx_enrollment_student", columnList = "student_id"),
        @Index(name = "idx_enrollment_classroom", columnList = "classroom_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @Column(length = 20)
    private String rollNumber; // optional

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;

    @Column(name = "enrolled_at")
    private LocalDate enrolledAt;
    @Column(name = "left_at")
    private LocalDate leftAt;
}

