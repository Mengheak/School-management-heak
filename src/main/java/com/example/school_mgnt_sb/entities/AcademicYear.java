package com.example.school_mgnt_sb.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "academic_years")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class AcademicYear {
    @Id @GeneratedValue
    @Column(name = "academic_year_id")
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Column(nullable = false,  name = "start_date")
    private LocalDate startDate;

    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean active = false;
}
