package com.example.school_mgnt_sb.dto.entrollment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateEnrollmentDto {
    @NotNull(message = "student_id can't be null")
    private UUID studentId;
    @NotNull(message = "student_id can't be null")
    private UUID classroomId;
    private String rollNumber;
    private LocalDate enrolledAt;
}
