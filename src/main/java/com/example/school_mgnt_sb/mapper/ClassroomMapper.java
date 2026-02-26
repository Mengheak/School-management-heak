package com.example.school_mgnt_sb.mapper;

import com.example.school_mgnt_sb.dto.classroom.ClassroomResponseDto;
import com.example.school_mgnt_sb.dto.classroom.CreateClassroomDto;
import com.example.school_mgnt_sb.entities.AcademicYear;
import com.example.school_mgnt_sb.entities.Classroom;
import com.example.school_mgnt_sb.entities.GradeLevel;
import com.example.school_mgnt_sb.entities.Teacher;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public ClassroomResponseDto toResponse(Classroom classroom) {
        ClassroomResponseDto response = new ClassroomResponseDto();
        response.setId(classroom.getId());
        response.setName(classroom.getName());
        response.setAcademicYearId(classroom.getAcademicYear().getId());
        response.setAcademicYearName(classroom.getAcademicYear().getName());
        response.setGradeLevelId(classroom.getGradeLevel().getId());
        response.setGradeLevelName(classroom.getGradeLevel().getName());

        Teacher teacher = classroom.getHomeroomTeacher();
        if (teacher != null) {
            response.setHomeroomTeacherId(teacher.getId());
            response.setHomeroomTeacherName(teacher.getFullName());
        } else {
            response.setHomeroomTeacherId(null);
            response.setHomeroomTeacherName(null);
        }

        return response;
    }

    public Classroom toEntity(CreateClassroomDto dto,
                              AcademicYear academicYear,
                              GradeLevel gradeLevel,
                              Teacher teacher) {
        Classroom entity = new Classroom();
        entity.setName(dto.getName());
        entity.setAcademicYear(academicYear);
        entity.setGradeLevel(gradeLevel);
        entity.setHomeroomTeacher(teacher);
        return entity;
    }
}
