package com.example.school_mgnt_sb.dto.classroom;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.UUID;

@Data
@JsonPropertyOrder({"classroom_id","name", "academic_year_id", "academic_year_name","grade_level_id",
        "grade_level_name", "homeroom_teacher_id", "homeroom_teacher_name"})
public class ClassroomResponseDto {
    @JsonProperty("classroom_id")
    UUID id;
    String name;

    @JsonProperty("academic_year_id")
    UUID academicYearId;

    @JsonProperty("academic_year_name")
    String academicYearName;

    @JsonProperty("grade_level_id")
    UUID gradeLevelId;

    @JsonProperty("grade_level_name")
    String gradeLevelName;

    @JsonProperty("homeroom_teacher_id")
    UUID homeroomTeacherId;

    @JsonProperty("homeroom_teacher_name")
    String homeroomTeacherName;
}
