package com.example.school_mgnt_sb.dto.classroom;


import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateClassroomDto {
    @Size(max = 30, message = "name length must be < 30") String name;
    UUID academicYearId;
    UUID gradeLevelId;
    UUID homeroomTeacherId;
}
