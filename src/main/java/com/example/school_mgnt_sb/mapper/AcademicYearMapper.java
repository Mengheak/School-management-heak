package com.example.school_mgnt_sb.mapper;


import com.example.school_mgnt_sb.dto.academic_year.AcademicYearResponse;
import com.example.school_mgnt_sb.entities.AcademicYear;
import org.springframework.stereotype.Component;

@Component
public class AcademicYearMapper {

    public AcademicYearResponse toResponse(AcademicYear entity){
        return new AcademicYearResponse(entity.getId(), entity.getName(), entity.getStartDate(), entity.getEndDate(), entity.isActive());
    }

}
