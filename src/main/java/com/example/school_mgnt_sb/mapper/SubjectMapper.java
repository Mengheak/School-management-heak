package com.example.school_mgnt_sb.mapper;


import com.example.school_mgnt_sb.dto.subject.CreateSubjectDto;
import com.example.school_mgnt_sb.dto.subject.SubjectResponseDto;
import com.example.school_mgnt_sb.entities.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {
    public SubjectResponseDto toResponse(Subject entity){
        SubjectResponseDto dto = new SubjectResponseDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        return dto;
    }
    public Subject toEntity(CreateSubjectDto dto){
        Subject entity = new Subject();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;
    }
}
