package com.example.school_mgnt_sb.dto.entrollment;

import com.example.school_mgnt_sb.common.enums.EnrollmentStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpdateEnrollmentDto {
    private UUID classroomId;
    private @Size(max = 20) String rollNumber;
    private EnrollmentStatus status;
    private LocalDate enrolledAt;
    private LocalDate leftAt;
}
