package com.example.school_mgnt_sb.dto.subject;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.UUID;

@Data
@JsonPropertyOrder({"subject_id", "subject_code", "name"})
public class SubjectResponseDto {
    @JsonProperty("subject_id")
    private UUID id;

    @JsonProperty("subject_code")
    private String code;

    private String name;
}
