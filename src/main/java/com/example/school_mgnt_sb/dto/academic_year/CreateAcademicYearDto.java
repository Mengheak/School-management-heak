package com.example.school_mgnt_sb.dto.academic_year;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateAcademicYearDto(@NotBlank(message = "name is required   ") @Size(max = 20) String name,
                                    @NotNull(message = "startDate cannot be null")
                                    LocalDate startDate,
                                    @NotNull(message = "endDate cannot be null")
                                    LocalDate endDate,
                                    Boolean active) {

}
