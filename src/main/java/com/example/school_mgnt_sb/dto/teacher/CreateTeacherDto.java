package com.example.school_mgnt_sb.dto.teacher;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTeacherDto {
    @NotBlank(message = "teacher fullname can not be blanked")
    @Size(max = 120, message = "teacher's fullname can't be larger than 120")
    private String fullName;

    @NotBlank(message = "teacher code can't be blank") @Size(max = 30, message = "teacher code can't be larger than 30")
    private String teacherCode;
    @Size(max = 50, message = "phone can't be larger than 50") String phone;
    @Email(message = "please input a valid email address")
    @Size(max = 120, message = "email can't be larger than 120") String email;
    Boolean active = true;
}
