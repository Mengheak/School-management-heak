package com.example.school_mgnt_sb.dto.academic_year;

import java.time.LocalDate;
import java.util.UUID;

public record AcademicYearResponse(UUID id,
                                   String name,
                                   LocalDate startDate,
                                   LocalDate endDate,
                                   boolean active) {
}
