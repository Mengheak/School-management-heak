package com.example.school_mgnt_sb.contollers;


import com.example.school_mgnt_sb.dto.teacher.CreateTeacherDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    @Autowired
    private TeacherService service;


    @PostMapping
    public ResponseEntity<BaseResponseModel> create(@Valid @RequestBody CreateTeacherDto dto){
        return this.service.create(dto);
    }

    @GetMapping
    public ResponseEntity<BaseResponseModel> list(){
        return this.service.list();
    }
}
