package com.example.school_mgnt_sb.services;


import com.example.school_mgnt_sb.dto.grade_level.CreateGradeLevelDto;
import com.example.school_mgnt_sb.dto.grade_level.UpdateGradeLevelDto;
import com.example.school_mgnt_sb.entities.GradeLevel;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.GradeLevelMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.GradeLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GradeLevelService {
    @Autowired
    private GradeLevelRepository  gradeLevelRepository;

    @Autowired
    private GradeLevelMapper gradeLevelMapper;

    public ResponseEntity<BaseResponseModel> create(CreateGradeLevelDto dto){
        if(this.gradeLevelRepository.existsByName(dto.getName())){
            throw new StatusException(HttpStatus.CONFLICT, dto.getName() + " grade level already exists");
        }
        GradeLevel saved = this.gradeLevelRepository.save(gradeLevelMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "create grade level successfully", this.gradeLevelMapper.toResponse(saved)));
    }
    public ResponseEntity<BaseResponseModel> getById(UUID id){
        GradeLevel fetched = this.gradeLevelRepository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "grade level is not found with id = "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get one grade level successfully", this.gradeLevelMapper.toResponse(fetched)));
    }
    public ResponseEntity<BaseResponseModel> listGradeLevel(){
        List<GradeLevel> fetched = this.gradeLevelRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get list grade level successfully", fetched.stream().map(gradeLevelMapper::toResponse).toList()));
    }
    public ResponseEntity<BaseResponseModel> updateGradeLevel(UUID id, UpdateGradeLevelDto dto){
        GradeLevel current = this.gradeLevelRepository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "grade level is not found with id = "+id));
        current.setName(dto.getName());
        this.gradeLevelRepository.save(current);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseModel("success", "updated level successfully"));
    }
    public ResponseEntity<BaseResponseModel> delete(UUID id){
        GradeLevel fetched = this.gradeLevelRepository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "grade level is not found with id = "+id));
        this.gradeLevelRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new BaseResponseModel("success", "deleted grade level with id = " + id +"successfully"));
    }
}
