package com.example.school_mgnt_sb.contollers;


import com.example.school_mgnt_sb.dto.classroom.CreateClassroomDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService service;


    @PostMapping
    public ResponseEntity<BaseResponseModel> create(@Valid @RequestBody CreateClassroomDto dto){
        return service.create(dto);
    }

    @GetMapping
    public ResponseEntity<BaseResponseModel> list(){
        return service.list();
    }
}
