package com.example.school_mgnt_sb.entities;


import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teachers", indexes = {
        @Index(name = "idx_teachers_code", columnList = "teacher_code")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Teacher {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, unique = true, length = 30)
    private String teacherCode;

    @Column(length = 50)
    private String phone;

    @Column(length = 120)
    private String email;

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "teacher")
    @Builder.Default
    private List<TeachingAssignment> teachingAssignments = new ArrayList<>();
}
