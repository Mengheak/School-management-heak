package com.example.school_mgnt_sb.dto.assessment;


import com.example.school_mgnt_sb.common.enums.AssessmentType;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateAssessmentDto {
    private AssessmentType type;
    private String title;
    private LocalDate date;
    @Positive private Double maxScore;
    private Double weight;
}
