package com.example.school_mgnt_sb.services;

import com.example.school_mgnt_sb.dto.classroom.CreateClassroomDto;
import com.example.school_mgnt_sb.entities.AcademicYear;
import com.example.school_mgnt_sb.entities.Classroom;
import com.example.school_mgnt_sb.entities.GradeLevel;
import com.example.school_mgnt_sb.entities.Teacher;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.ClassroomMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.AcademicYearRepository;
import com.example.school_mgnt_sb.repositories.ClassroomRepository;
import com.example.school_mgnt_sb.repositories.GradeLevelRepository;
import com.example.school_mgnt_sb.repositories.TeacherRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ClassroomMapper mapper;


    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private GradeLevelRepository gradeLevelRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public ResponseEntity<BaseResponseModel> create(CreateClassroomDto dto) {
        if(classroomRepository.existsByName(dto.getName())){
            throw new StatusException(HttpStatus.CONFLICT, "classroom with name = " + dto.getName()+ " already exists");
        }

        AcademicYear existingAcd = academicYearRepository
                .findById(dto.getAcademicYearId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't find academic year with id = "+ dto.getAcademicYearId()));
        GradeLevel existingGrade = gradeLevelRepository
                .findById(dto.getGradeLevelId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't find grade level with id = "+ dto.getGradeLevelId()));


        Teacher existingTeacher = null;
        if (dto.getHomeroomTeacherId() != null) {
            existingTeacher = teacherRepository
                    .findById(dto.getHomeroomTeacherId())
                    .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND,
                            "can't find teacher with id = " + dto.getHomeroomTeacherId()));
        }
        Classroom saved = classroomRepository.save(mapper.toEntity(dto, existingAcd, existingGrade, existingTeacher));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "created a classroom successfully", mapper.toResponse(saved)));

    }
    public ResponseEntity<BaseResponseModel> list(){
        List<Classroom> fetched = classroomRepository.findAll();
        return ResponseEntity.ok(new ApiResponseModel<>("success", "get all classrooms successfully", fetched.stream().map(mapper::toResponse).toList()));
    }
}
