package com.example.school_mgnt_sb.dto.student;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStudentDto {
    @NotBlank(message = "full name can't be blanked")
    @Size(max = 120, message = "full name must be < 120") String fullName;

    @NotBlank(message = "student Code can't be blanked")
    @Size(max = 30, message = "full name must be < 30") String studentCode;

    LocalDate dateOfBirth;
    Boolean active;

}
