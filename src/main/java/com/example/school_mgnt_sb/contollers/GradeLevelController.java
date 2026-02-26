package com.example.school_mgnt_sb.contollers;


import com.example.school_mgnt_sb.dto.grade_level.CreateGradeLevelDto;
import com.example.school_mgnt_sb.dto.grade_level.UpdateGradeLevelDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.GradeLevelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/grade-levels")
public class GradeLevelController {
    @Autowired
    private GradeLevelService service;

    @GetMapping
    public ResponseEntity<BaseResponseModel> list(){
        return this.service.listGradeLevel();
    }
    @PostMapping
    public ResponseEntity<BaseResponseModel> create(@Valid @RequestBody CreateGradeLevelDto dto){
        return this.service.create(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseModel> update(@PathVariable("id") UUID id, @Valid  @RequestBody UpdateGradeLevelDto dto){
        return this.service.updateGradeLevel(id, dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseModel> getOne(@PathVariable("id") UUID id){
        return this.service.getById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseModel> delete(@PathVariable("id") UUID id){
        return this.service.delete(id);
    }
}
