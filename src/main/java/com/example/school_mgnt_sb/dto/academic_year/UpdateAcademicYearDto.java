package com.example.school_mgnt_sb.dto.academic_year;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateAcademicYearDto(
        @Size(max = 20) String name,
        @NotNull(message = "startDate can not be null")
        LocalDate startDate,
        @NotNull(message = "endDate can not be null")
        LocalDate endDate,
        Boolean active
) {
}
