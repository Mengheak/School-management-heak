package com.example.school_mgnt_sb.dto.attendance;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UpsertAttendanceRecordsDto {
    @NotEmpty(message = "attendance records list can't be empty")
    private List<AttendanceRecordUpsertDto> records;
}
