package com.example.school_mgnt_sb.entities.assessment;

import com.example.school_mgnt_sb.entities.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "scores",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_score_assessment_student", columnNames = {"assessment_id", "student_id"})
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Score {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private double value;

    @Column(length = 255)
    private String remark;
}
