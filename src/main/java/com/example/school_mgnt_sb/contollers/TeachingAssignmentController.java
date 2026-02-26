package com.example.school_mgnt_sb.contollers;


import com.example.school_mgnt_sb.dto.teaching_assignment.CreateTeachingAssignmentDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.TeachingAssignmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teaching-assignments")
public class TeachingAssignmentController {

    @Autowired
    private TeachingAssignmentService service;

    @PostMapping
    public ResponseEntity<BaseResponseModel> create(@Valid @RequestBody CreateTeachingAssignmentDto dto){
        return this.service.create(dto);
    }

    @GetMapping
    public ResponseEntity<BaseResponseModel> list(){
        return this.service.list();
    }
}
