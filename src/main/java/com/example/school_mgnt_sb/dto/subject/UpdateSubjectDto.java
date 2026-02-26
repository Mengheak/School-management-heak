package com.example.school_mgnt_sb.dto.subject;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateSubjectDto {
    @Size(max = 20, message = "code length must be less than 20") String code;
    @Size(max = 120, message = "name length must be less than 120") String name;
}
