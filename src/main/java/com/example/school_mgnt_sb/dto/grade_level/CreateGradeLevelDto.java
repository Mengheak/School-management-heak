package com.example.school_mgnt_sb.dto.grade_level;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateGradeLevelDto {
    @NotNull(message = "Name is required") @Size(max = 50)
    String name;
}
