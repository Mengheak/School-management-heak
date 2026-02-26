package com.example.school_mgnt_sb.dto.score;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ScoreResponseDto {
    @JsonProperty("score_id")
    private UUID id;

    @JsonProperty("student_id")
    private UUID studentId;

    @JsonProperty("student_name")
    private String studentName;

    @JsonProperty("student_code")
    private String studentCode;

    private double value;

    private String remark;
}
