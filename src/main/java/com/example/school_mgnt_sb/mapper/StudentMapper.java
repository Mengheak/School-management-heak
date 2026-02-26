package com.example.school_mgnt_sb.mapper;

import com.example.school_mgnt_sb.dto.student.CreateStudentDto;
import com.example.school_mgnt_sb.dto.student.StudentResponseDto;
import com.example.school_mgnt_sb.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentResponseDto toResponse(Student entity){
        StudentResponseDto resp = new StudentResponseDto();
        resp.setId(entity.getId());
        resp.setActive(entity.isActive());
        resp.setFullName(entity.getFullName());
        resp.setStudentCode(entity.getStudentCode());
        resp.setDateOfBirth(entity.getDateOfBirth());
        return resp;
    }
    public Student toEntity(CreateStudentDto dto){
        Student entity = new Student();
        entity.setFullName(dto.getFullName());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setStudentCode(dto.getStudentCode());
        if(dto.getActive() == null){
            entity.setActive(true);
        }else{
            entity.setActive(dto.getActive());
        }
        return entity;
    }
}
