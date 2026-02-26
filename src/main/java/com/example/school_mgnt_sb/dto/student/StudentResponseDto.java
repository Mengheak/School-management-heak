package com.example.school_mgnt_sb.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonPropertyOrder({"student_id", "full_name", "student_code", "date_of_birth"})
public class StudentResponseDto {
    @JsonProperty("student_id")
    private UUID id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("student_code")
    private String studentCode;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    private boolean active;
}
