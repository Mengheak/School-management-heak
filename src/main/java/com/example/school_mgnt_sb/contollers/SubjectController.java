package com.example.school_mgnt_sb.contollers;


import com.example.school_mgnt_sb.dto.subject.CreateSubjectDto;
import com.example.school_mgnt_sb.dto.subject.UpdateSubjectDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {
    @Autowired
    private SubjectService service;

    @GetMapping
    public ResponseEntity<BaseResponseModel> list(){
        return this.service.list();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseModel> getOne(@PathVariable("id") UUID id){
        return this.service.getOne(id);
    }
    @PostMapping
    public ResponseEntity<BaseResponseModel> create(@Valid @RequestBody CreateSubjectDto dto){
        return this.service.create(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseModel> update(@PathVariable("id") UUID id,@RequestBody(required = false) UpdateSubjectDto dto){
        return this.service.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseModel> delete(@PathVariable("id") UUID id){
        return this.service.delete(id);
    }

}
