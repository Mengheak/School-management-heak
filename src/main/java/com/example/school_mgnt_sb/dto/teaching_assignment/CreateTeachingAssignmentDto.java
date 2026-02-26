package com.example.school_mgnt_sb.dto.teaching_assignment;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateTeachingAssignmentDto {
    @NotNull(message = "classroom id must be specified")
    private UUID classroomId;
    @NotNull(message = "subject id must be specified")
    private UUID subjectId;
    @NotNull(message = "teacher id must be specified")
    private UUID teacherId;
}
