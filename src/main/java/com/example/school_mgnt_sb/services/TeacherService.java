package com.example.school_mgnt_sb.services;

import com.example.school_mgnt_sb.dto.teacher.CreateTeacherDto;
import com.example.school_mgnt_sb.dto.teacher.UpdateTeacherDto;
import com.example.school_mgnt_sb.entities.Teacher;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.TeacherMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherMapper mapper;


    public ResponseEntity<BaseResponseModel> create(CreateTeacherDto dto){
        if(this.repository.existsByTeacherCode(dto.getTeacherCode())){
            throw new StatusException(HttpStatus.CONFLICT, "teacher with code = "+dto.getTeacherCode()+" already exists");
        }
        Teacher saved = this.repository.save(this.mapper.toEntity(dto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseModel<>("success", "created teacher successfully", this.mapper.toResponse(saved)));
    }

    public ResponseEntity<BaseResponseModel> list(){
        List<Teacher> fetched = this.repository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseModel<>("success", "get all teachers successfully", fetched.stream().map(mapper::toResponse).toList()));
    }
    public ResponseEntity<BaseResponseModel> getOne(UUID id){
        Teacher fetched = this.repository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't find teacher with id = "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get one teacher with id = " + id + " successfully", this.mapper.toResponse(fetched)));
    }

    public ResponseEntity<BaseResponseModel> update(UUID id, UpdateTeacherDto dto){
        if(this.repository.existsByTeacherCode(dto.getTeacherCode())){
            throw new StatusException(HttpStatus.CONFLICT, "teacher with code = "+dto.getTeacherCode()+" already exists");
        }
        Teacher current = this.repository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't find teacher with id = "+id));
        current.setFullName(dto.getFullName());
        current.setTeacherCode(dto.getTeacherCode());
        current.setPhone(dto.getPhone());
        current.setEmail(dto.getEmail());
        current.setActive(dto.getActive());
        Teacher updated = this.repository.save(current);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseModel<>("success","updated teacher successfully", this.mapper.toResponse(updated)));
    }

}
