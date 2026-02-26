package com.example.school_mgnt_sb.dto.attendance;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class AttendanceSessionResponseDto {
    @JsonProperty("attendance_session_id")
    private UUID id;

    @JsonProperty("teaching_assignment_id")
    private UUID studentId;

    private LocalDate date;

    private String note;

    List<AttendanceRecordResponseDto> records;

}
