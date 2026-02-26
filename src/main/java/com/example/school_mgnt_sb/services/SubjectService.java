package com.example.school_mgnt_sb.services;


import com.example.school_mgnt_sb.dto.subject.CreateSubjectDto;
import com.example.school_mgnt_sb.dto.subject.UpdateSubjectDto;
import com.example.school_mgnt_sb.entities.Subject;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.SubjectMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository repository;

    @Autowired
    private SubjectMapper mapper;
    public ResponseEntity<BaseResponseModel> create(CreateSubjectDto dto){
        Subject saved = this.repository.save(this.mapper.toEntity(dto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseModel<>("success", "created subject successfully", this.mapper.toResponse(saved)));
    }
    public ResponseEntity<BaseResponseModel> list(){
        List<Subject> fetched = this.repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get all subjects successfully", fetched.stream().map(mapper::toResponse).toList()));
    }
    public ResponseEntity<BaseResponseModel> getOne(UUID id){
        Subject fetched = this.repository.findById(id).orElseThrow(()-> new StatusException(HttpStatus.NOT_FOUND, "can not find subject with it = " + id));
        return ResponseEntity
                .status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get subject with id = "+id+ "successfully", this.mapper.toResponse(fetched)));
    }
    public ResponseEntity<BaseResponseModel> update(UUID id, UpdateSubjectDto dto){
        Subject current = this.repository.findById(id).orElseThrow(()-> new StatusException(HttpStatus.NOT_FOUND, "can not find subject with it = " + id));
        current.setName(dto.getName());
        current.setCode(dto.getCode());
        Subject saved = this.repository.save(current);
        return  ResponseEntity
                .status(HttpStatus.OK).body(new ApiResponseModel<>("success", "updated subject with id = "+ id + "successfully", saved));
    }
    public ResponseEntity<BaseResponseModel> delete(UUID id){
        this.repository.findById(id).orElseThrow(()-> new StatusException(HttpStatus.NOT_FOUND, "can not find subject with it = " + id));
        this.repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new BaseResponseModel("success", "deleted ssubject with id = "+id + "successfully"));
    }
}
