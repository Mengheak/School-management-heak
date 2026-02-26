package com.example.school_mgnt_sb.dto.attendance;


import com.example.school_mgnt_sb.common.enums.AttendanceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class AttendanceRecordResponseDto {
        @JsonProperty("attendance_record_id")
        private UUID id;
        @JsonProperty("student_id")
        private UUID studentId;
        @JsonProperty("student_name")
        private String studentName;
        @JsonProperty("student_code")
        private String studentCode;
        private AttendanceStatus status;
        private String remark;
}
