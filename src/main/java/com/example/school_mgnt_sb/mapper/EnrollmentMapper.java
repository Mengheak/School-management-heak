package com.example.school_mgnt_sb.mapper;


import com.example.school_mgnt_sb.common.enums.EnrollmentStatus;
import com.example.school_mgnt_sb.dto.entrollment.CreateEnrollmentDto;
import com.example.school_mgnt_sb.dto.entrollment.EnrollmentResponseDto;
import com.example.school_mgnt_sb.entities.Classroom;
import com.example.school_mgnt_sb.entities.Student;
import com.example.school_mgnt_sb.entities.relationship.Enrollment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EnrollmentMapper {
    public EnrollmentResponseDto toResponse(Enrollment entity){
        EnrollmentResponseDto dto = new EnrollmentResponseDto();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setStudentName(entity.getStudent().getFullName());
        dto.setClassroomId(entity.getClassroom().getId());
        dto.setClassroomName(entity.getClassroom().getName());
        dto.setAcademicYearId(entity.getAcademicYear().getId());
        dto.setAcademicYearName(entity.getAcademicYear().getName());
        dto.setEnrolledAt(entity.getEnrolledAt());
        dto.setStatus(entity.getStatus());
        dto.setLeftAt(entity.getLeftAt());
        return dto;
    }

    public Enrollment toEntity (CreateEnrollmentDto dto, Classroom classroom, Student student){
        Enrollment ent = new Enrollment();
        ent.setAcademicYear(classroom.getAcademicYear());
        ent.setStudent(student);
        ent.setRollNumber(dto.getRollNumber());
        ent.setClassroom(classroom);
        ent.setStatus(EnrollmentStatus.ACTIVE);
        ent.setEnrolledAt(dto.getEnrolledAt() != null ? dto.getEnrolledAt() : LocalDate.now());
        return ent;
    }
}
