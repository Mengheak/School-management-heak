package com.example.school_mgnt_sb.dto.assessment;

import com.example.school_mgnt_sb.common.enums.AssessmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonPropertyOrder({"assessment_id", "teaching_assignment_id", "type", "title", "date", "max_score", "weight"})
public class AssessmentResponseDto {
    @JsonProperty("assessment_id")
    private UUID id;

    @JsonProperty("teaching_assignment_id")
    private UUID teachingAssignmentId;

    private AssessmentType type;

    private String title;

    private LocalDate date;

    @JsonProperty("max_score")
    private Double maxScore;

    private Double weight;
}
