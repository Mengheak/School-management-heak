package com.example.school_mgnt_sb.dto.grade_level;


import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateGradeLevelDto {
    @Size(max = 50) String name;
}
