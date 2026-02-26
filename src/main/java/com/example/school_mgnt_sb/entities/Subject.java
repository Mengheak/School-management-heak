package com.example.school_mgnt_sb.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "subjects",
        indexes = {
                @Index(name = "idx_subjects_code", columnList = "code")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String code; // e.g. MATH

    @Column(nullable = false, length = 120)
    private String name; // e.g. Mathematics

}
