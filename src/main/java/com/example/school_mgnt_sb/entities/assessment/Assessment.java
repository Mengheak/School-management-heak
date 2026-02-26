package com.example.school_mgnt_sb.entities.assessment;

import com.example.school_mgnt_sb.common.enums.AssessmentType;
import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "assessments",
        indexes = {
                @Index(name = "idx_assessment_ta", columnList = "teaching_assignment_id")
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Assessment {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "teaching_assignment_id", nullable = false)
    private TeachingAssignment teachingAssignment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AssessmentType type;

    @Column(nullable = false, length = 120)
    private String title; // e.g. "Midterm Exam"

    private LocalDate date;

    @Column(nullable = false)
    private double maxScore = 100.0;

    // optional grading weight (0-1 or 0-100, your choice)
    private Double weight;

    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Score> scores = new ArrayList<>();
}

