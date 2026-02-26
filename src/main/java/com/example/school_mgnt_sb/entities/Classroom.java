package com.example.school_mgnt_sb.entities;


import com.example.school_mgnt_sb.entities.relationship.Enrollment;
import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "classrooms",
        uniqueConstraints = {
                // same classroom name can exist in different years, but not duplicated in the same year
                @UniqueConstraint(name = "uq_classroom_year_name", columnNames = {"academic_year_id", "name"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Classroom {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 30)
    private String name; // e.g. "7A"


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_level_id", nullable = false)
    private GradeLevel gradeLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homeroom_teacher_id")
    private Teacher homeroomTeacher;


    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "classroom")
    @Builder.Default
    private List<TeachingAssignment> teachingAssignments = new ArrayList<>();
}
