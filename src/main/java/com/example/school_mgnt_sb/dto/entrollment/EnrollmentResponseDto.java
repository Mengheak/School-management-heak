package com.example.school_mgnt_sb.dto.entrollment;


import com.example.school_mgnt_sb.common.enums.EnrollmentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonPropertyOrder({"enrollment_id", "student_id", "student_name","classroom_id", "classroom_name", "academic_year_id",
        "academic_year_name","roll_number","status", "enrolled_at", "left_at"})
public class EnrollmentResponseDto {
    @JsonProperty("enrollment_id")
    private UUID id;

    @JsonProperty("student_id")
    private UUID studentId;

    @JsonProperty("student_name")
    private String studentName;

    @JsonProperty("classroom_id")
    private UUID classroomId;

    @JsonProperty("classroom_name")
    private String classroomName;

    @JsonProperty("academic_year_id")
    private UUID academicYearId;

    @JsonProperty("academic_year_name")
    private String academicYearName;


    @JsonProperty("roll_number")
    private String rollNumber;

    private EnrollmentStatus status;

    @JsonProperty("enrolled_at")
    LocalDate enrolledAt;

    @JsonProperty("left_at")
    LocalDate leftAt;
}
