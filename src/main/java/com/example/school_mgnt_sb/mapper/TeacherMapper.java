package com.example.school_mgnt_sb.mapper;


import com.example.school_mgnt_sb.dto.teacher.CreateTeacherDto;
import com.example.school_mgnt_sb.dto.teacher.TeacherResponseDto;
import com.example.school_mgnt_sb.entities.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public TeacherResponseDto toResponse(Teacher entity){
        TeacherResponseDto response = new TeacherResponseDto();
        response.setId(entity.getId());
        response.setFullName(entity.getFullName());
        response.setCode(entity.getTeacherCode());
        response.setPhone(entity.getPhone());
        response.setEmail(entity.getEmail());
        response.setActive(entity.isActive());
        return response;
    }
    public Teacher toEntity(CreateTeacherDto dto){
        Teacher ent = new Teacher();
        ent.setFullName(dto.getFullName());
        ent.setTeacherCode(dto.getTeacherCode());
        ent.setPhone(dto.getPhone());
        ent.setEmail(dto.getEmail());
        ent.setActive(dto.getActive());
        return ent;
    }
}
