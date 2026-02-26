package com.example.school_mgnt_sb.dto.role;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateRoleDto {
    @NotNull(message = "Name must not be null")
    @Length(max = 30, message = "Can not input role with length > 30")
    private String name;
}
