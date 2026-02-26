package com.example.school_mgnt_sb.dto.score;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.UUID;

@Data
public class ScoreUpsertDto {
    @NotNull
    private UUID studentId;

    @NotNull
    @PositiveOrZero
    private Double value;

    private String remark;
}
