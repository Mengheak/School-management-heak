package com.example.school_mgnt_sb.dto.teacher;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.UUID;

@Data
@JsonPropertyOrder({"teacher_id","teacher_code", "full_name", "phone", "email", "active"})
public class TeacherResponseDto {
    @JsonProperty("teacher_id")
    private UUID id;

    @JsonProperty("teacher_code")
    private String code;

    @JsonProperty("full_name")
    private String fullName;

    private String phone;
    private String email;

    private boolean active;
}
