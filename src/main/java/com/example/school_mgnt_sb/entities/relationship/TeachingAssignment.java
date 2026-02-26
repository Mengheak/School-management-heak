package com.example.school_mgnt_sb.entities.relationship;


import com.example.school_mgnt_sb.entities.AcademicYear;
import com.example.school_mgnt_sb.entities.Classroom;
import com.example.school_mgnt_sb.entities.Subject;
import com.example.school_mgnt_sb.entities.Teacher;
import com.example.school_mgnt_sb.entities.assessment.Assessment;
import com.example.school_mgnt_sb.entities.attendance.AttendanceSession;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "teaching_assignments",
        uniqueConstraints = {
                // One unique teaching assignment per (year, classroom, subject, teacher)
                @UniqueConstraint(
                        name = "uq_ta_year_class_subject_teacher",
                        columnNames = {"academic_year_id", "classroom_id", "subject_id", "teacher_id"}
                )
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeachingAssignment {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "teachingAssignment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AttendanceSession> attendanceSessions = new ArrayList<>();

    @OneToMany(mappedBy = "teachingAssignment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Assessment> assessments = new ArrayList<>();
}
