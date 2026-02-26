package com.example.school_mgnt_sb.services;


import com.example.school_mgnt_sb.config.ApplicationConfig;
import com.example.school_mgnt_sb.dto.base.PaginatedResponse;
import com.example.school_mgnt_sb.dto.student.CreateStudentDto;
import com.example.school_mgnt_sb.dto.student.StudentResponseDto;
import com.example.school_mgnt_sb.dto.student.UpdateStudentDto;
import com.example.school_mgnt_sb.entities.Student;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.StudentMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    @Autowired
    private ApplicationConfig applicationConfig;

    public ResponseEntity<BaseResponseModel> create(CreateStudentDto dto){
        if(repository.existsByStudentCode(dto.getStudentCode())){
        throw new StatusException(HttpStatus.CONFLICT, "student code already exists");
    }
        Student saved = repository.save(mapper.toEntity(dto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseModel<>("success", "created student successfully", mapper.toResponse(saved)));
    }

    public PaginatedResponse list(Pageable pageable){
        Page<Student> studentPages = repository.findAll(pageable);
        Page<StudentResponseDto> studentPagesDto = studentPages.map(mapper::toResponse);
        System.out.println(applicationConfig.getPagination().getUrlByResource("student"));
        return PaginatedResponse.from(studentPagesDto, applicationConfig.getPagination().getUrlByResource("student"));
         }

    public ResponseEntity<BaseResponseModel> getOne(UUID id){
        Student fetched = repository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't get student with id = " + id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get one student successfully", mapper.toResponse(fetched)));
    }

    public ResponseEntity<BaseResponseModel> update(UUID id, UpdateStudentDto dto){
        Student fetched = repository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't get student with id = " + id));
        if(dto.getDateOfBirth() != null){
            fetched.setDateOfBirth(dto.getDateOfBirth());
        }
        if(dto.getFullName() != null && !dto.getFullName().isBlank()){
            fetched.setFullName(dto.getFullName());
        }
        if(dto.getStudentCode() != null && !dto.getStudentCode().isBlank()){
            if(repository.existsByStudentCode(dto.getStudentCode())){
                throw new StatusException(HttpStatus.CONFLICT, "student code already exists");
            }
            fetched.setStudentCode(dto.getStudentCode());
        }

        if (dto.getDateOfBirth() != null) {
            fetched.setDateOfBirth(dto.getDateOfBirth());
        }
        if (dto.getActive() != null) {
            fetched.setActive(dto.getActive());
        }

        Student saved = repository.save(fetched);
        System.out.println(saved.isActive());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseModel<>("success", "updated student successfully", mapper.toResponse(saved)));
    }
}
