package com.example.school_mgnt_sb.dto.subject;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSubjectDto {
    @NotBlank(message = "code can not be blank")
    @Size(max = 20)
    private String code;

    @NotBlank(message = "name can not be blank")
    @Size(max = 120)
    private String name;

}
