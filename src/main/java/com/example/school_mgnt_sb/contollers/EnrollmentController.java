package com.example.school_mgnt_sb.contollers;

import com.example.school_mgnt_sb.dto.entrollment.CreateEnrollmentDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService service;


    @PostMapping
    public ResponseEntity<BaseResponseModel> create(@Valid @RequestBody CreateEnrollmentDto dto){
        return service.create(dto);
    }

    @GetMapping
    public ResponseEntity<BaseResponseModel> list(){
        return service.list();
    }
}
