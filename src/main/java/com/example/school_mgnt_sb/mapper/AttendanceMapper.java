package com.example.school_mgnt_sb.mapper;


import com.example.school_mgnt_sb.dto.attendance.AttendanceRecordResponseDto;
import com.example.school_mgnt_sb.dto.attendance.AttendanceSessionResponseDto;
import com.example.school_mgnt_sb.dto.attendance.CreateAttendanceSessionDto;
import com.example.school_mgnt_sb.entities.attendance.AttendanceRecord;
import com.example.school_mgnt_sb.entities.attendance.AttendanceSession;

import org.springframework.stereotype.Component;



@Component
public class AttendanceMapper {

    public AttendanceRecordResponseDto toRecordResponse(AttendanceRecord record){
        AttendanceRecordResponseDto dto = new AttendanceRecordResponseDto();
        dto.setId(record.getId());
        dto.setStatus(record.getStatus());
        dto.setRemark(record.getRemark());

        if (record.getStudent() != null) {
            dto.setStudentId(record.getStudent().getId());
            dto.setStudentCode(record.getStudent().getStudentCode());
            dto.setStudentName(record.getStudent().getFullName());
        }
        return dto;
    }

    public AttendanceSessionResponseDto toResponseBasic(AttendanceSession session){
        AttendanceSessionResponseDto dto = new AttendanceSessionResponseDto();
        dto.setId(session.getId());
        dto.setDate(session.getDate());
        dto.setNote(session.getNote());
        dto.setRecords(java.util.List.of());
        return dto;
    }

    public AttendanceSessionResponseDto toResponseWithRecords(AttendanceSession session){
        AttendanceSessionResponseDto dto = toResponseBasic(session);

        if (session.getRecords() != null) {
            dto.setRecords(session.getRecords().stream().map(this::toRecordResponse).toList());
        }
        return dto;
    }

    public AttendanceSession toEntity(CreateAttendanceSessionDto dto) {
        AttendanceSession attendanceSession = new AttendanceSession();
        attendanceSession.setDate(dto.getDate());
        attendanceSession.setNote(dto.getNote());
        return attendanceSession;
    }
}