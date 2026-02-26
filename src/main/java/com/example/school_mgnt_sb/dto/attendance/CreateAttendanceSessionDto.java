package com.example.school_mgnt_sb.dto.attendance;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateAttendanceSessionDto {
    @NotNull(message = "Teaching assignment id can't be null")
    UUID teachingAssignmentId;
    @NotNull(message = "Date can't be null <yyyy-mm-dd>")
    LocalDate date;
    String note;
}
