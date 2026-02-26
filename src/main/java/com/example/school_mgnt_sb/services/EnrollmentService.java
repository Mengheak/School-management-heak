package com.example.school_mgnt_sb.services;


import com.example.school_mgnt_sb.dto.entrollment.CreateEnrollmentDto;
import com.example.school_mgnt_sb.entities.Classroom;
import com.example.school_mgnt_sb.entities.Student;
import com.example.school_mgnt_sb.entities.relationship.Enrollment;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.EnrollmentMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.ClassroomRepository;
import com.example.school_mgnt_sb.repositories.EnrollmentRepository;
import com.example.school_mgnt_sb.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository repository;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ClassroomRepository classroomRepo;
    @Autowired
    private EnrollmentMapper mapper;

    public ResponseEntity<BaseResponseModel> create(CreateEnrollmentDto dto){
        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't find student with id = " + dto.getStudentId()));

        Classroom classroom = classroomRepo.findById(dto.getClassroomId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't find classroom with id = " + dto.getClassroomId()));

        UUID yearId = classroom.getAcademicYear().getId();

        if(repository.existsByStudent_IdAndAcademicYear_Id(student.getId(), yearId)){
            throw new StatusException(HttpStatus.BAD_REQUEST, "student already enrolled in this academic year: "+yearId);
        }
        Enrollment e = repository.save(mapper.toEntity(dto, classroom, student));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "student enrolled successfully", mapper.toResponse(e)));
    }

    public ResponseEntity<BaseResponseModel> list(){
        List<Enrollment> fetched = repository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseModel<>("success", "get all enrollments successfully", fetched.stream().map(mapper::toResponse).toList()));
    }
}
