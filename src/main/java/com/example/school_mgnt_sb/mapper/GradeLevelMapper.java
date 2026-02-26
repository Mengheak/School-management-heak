package com.example.school_mgnt_sb.mapper;


import com.example.school_mgnt_sb.dto.grade_level.CreateGradeLevelDto;
import com.example.school_mgnt_sb.dto.grade_level.GradeLevelResponse;
import com.example.school_mgnt_sb.entities.GradeLevel;
import org.springframework.stereotype.Component;

@Component
public class GradeLevelMapper {

    public GradeLevel toEntity(CreateGradeLevelDto dto){
        GradeLevel entity = new GradeLevel();
        entity.setName(dto.getName());
        return entity;
    }
    public GradeLevelResponse toResponse(GradeLevel entity){
        GradeLevelResponse response = new GradeLevelResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }

}
