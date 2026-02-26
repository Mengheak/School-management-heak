package com.example.school_mgnt_sb.dto.score;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UpsertScoreDto {
    @NotEmpty private List<ScoreUpsertDto> scores;
}
