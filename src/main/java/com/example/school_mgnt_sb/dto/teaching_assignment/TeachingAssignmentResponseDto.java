package com.example.school_mgnt_sb.dto.teaching_assignment;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class TeachingAssignmentResponseDto {
    @JsonProperty("teaching_assignment_id")
    private UUID id;
    @JsonProperty("academic_year_id")
    private UUID academicYearId;
    @JsonProperty("academic_year_name")
    private String academicYearName;
    @JsonProperty("classroom_id")
    private UUID classroomId;
    @JsonProperty("classroom_name")
    private String classroomName;
    @JsonProperty("subject_id")
    private UUID subjectId;
    @JsonProperty("subject_name")
    private String subjectName;
    @JsonProperty("subject_code")
    private String subjectCode;
    @JsonProperty("teacher_id")
    private UUID teacherId;
    @JsonProperty("teacher_name")
    private String teacherName;
    @JsonProperty("teacher_code")
    private String teacherCode;
}
