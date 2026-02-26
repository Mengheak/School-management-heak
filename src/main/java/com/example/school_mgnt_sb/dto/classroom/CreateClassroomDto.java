package com.example.school_mgnt_sb.dto.classroom;


import com.example.school_mgnt_sb.repositories.TeacherRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
public class CreateClassroomDto {


    @NotBlank(message = "name can't be blanked")
    @Size(max = 30, message = "name can't be larger than 30") String name;
    @NotNull(message = "academicYearId is not nullable")
    UUID academicYearId;
    @NotNull(message = "gradeLevelId is not nullable")
    UUID gradeLevelId;
    UUID homeroomTeacherId;
}
