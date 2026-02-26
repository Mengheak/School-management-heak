package com.example.school_mgnt_sb.dto.teaching_assignment;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateTeachingAssignmentDto {
    @NotNull(message = "teacher id must be specified")
    private UUID teacherId;
}
