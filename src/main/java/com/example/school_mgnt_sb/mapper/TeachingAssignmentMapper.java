package com.example.school_mgnt_sb.mapper;


import com.example.school_mgnt_sb.dto.teaching_assignment.CreateTeachingAssignmentDto;
import com.example.school_mgnt_sb.dto.teaching_assignment.TeachingAssignmentResponseDto;
import com.example.school_mgnt_sb.entities.AcademicYear;
import com.example.school_mgnt_sb.entities.Classroom;
import com.example.school_mgnt_sb.entities.Subject;
import com.example.school_mgnt_sb.entities.Teacher;
import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import org.springframework.stereotype.Component;

@Component
public class TeachingAssignmentMapper {
    public TeachingAssignmentResponseDto toResponse (TeachingAssignment entity){
        TeachingAssignmentResponseDto dto = new TeachingAssignmentResponseDto();
        dto.setId(entity.getId());
        dto.setAcademicYearId(entity.getAcademicYear().getId());
        dto.setAcademicYearName(entity.getAcademicYear().getName());
        dto.setClassroomId(entity.getClassroom().getId());
        dto.setClassroomName(entity.getClassroom().getName());
        dto.setSubjectId(entity.getSubject().getId());
        dto.setSubjectCode(entity.getSubject().getCode());
        dto.setSubjectName(entity.getSubject().getName());
        dto.setTeacherId(entity.getTeacher().getId());
        dto.setTeacherName(entity.getTeacher().getFullName());
        dto.setTeacherCode(entity.getTeacher().getTeacherCode());
        return dto;
    }
    public TeachingAssignment toEntity(CreateTeachingAssignmentDto dto, Subject subject, Teacher teacher, Classroom classroom){
        TeachingAssignment entity = new TeachingAssignment();
        entity.setTeacher(teacher);
        entity.setClassroom(classroom);
        entity.setAcademicYear(classroom.getAcademicYear());
        entity.setSubject(subject);
        return entity;
    }
}
