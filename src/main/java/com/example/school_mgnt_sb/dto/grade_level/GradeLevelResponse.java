package com.example.school_mgnt_sb.dto.grade_level;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

@Data
public class GradeLevelResponse {
    @JsonProperty("grade_level_id")
    private UUID id;

    private String name;
}
