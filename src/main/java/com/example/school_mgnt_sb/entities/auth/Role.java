package com.example.school_mgnt_sb.entities.auth;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Role {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 30)
    private String name; // ADMIN, TEACHER, STUDENT
}
