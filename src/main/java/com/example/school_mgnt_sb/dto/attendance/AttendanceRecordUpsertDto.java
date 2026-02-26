package com.example.school_mgnt_sb.dto.attendance;


import com.example.school_mgnt_sb.common.enums.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;


@Data
public class AttendanceRecordUpsertDto {
    @NotNull(message = "Student Id can't be null")
    private UUID studentId;

    @NotNull(message = "Attendance status can't be null")
    private AttendanceStatus status;

    private String remark;
}
