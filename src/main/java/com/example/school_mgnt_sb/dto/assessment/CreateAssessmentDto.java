package com.example.school_mgnt_sb.dto.assessment;


import com.example.school_mgnt_sb.common.enums.AssessmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateAssessmentDto {
    @NotNull(message = "teaching assignment Id can't be null")
    private UUID teachingAssignmentId;

    @NotNull(message = "type can't be null")
    private AssessmentType type;

    @NotBlank(message = "title can't be blank")
    private String title;

    private LocalDate date;

    @NotNull(message = "maxScore can't be null") @Positive(message = "this can't be negative")
    private Double maxScore;


    private Double weight;

}
