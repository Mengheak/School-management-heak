package com.example.school_mgnt_sb.contollers;

import com.example.school_mgnt_sb.dto.base.PaginatedResponse;
import com.example.school_mgnt_sb.dto.base.Response;
import com.example.school_mgnt_sb.dto.student.CreateStudentDto;
import com.example.school_mgnt_sb.dto.student.StudentResponseDto;
import com.example.school_mgnt_sb.dto.student.UpdateStudentDto;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService service;


    @PostMapping
    public ResponseEntity<BaseResponseModel> create(@Valid @RequestBody CreateStudentDto dto){
        return service.create(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public  ResponseEntity<Response> list(@PageableDefault(size = 10, page = 0, direction = Sort.Direction.DESC) Pageable pageable){
        PaginatedResponse<StudentResponseDto> studentPages = service.list(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200", "success", "successfully retrieved students with pagination", studentPages));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseModel> getOne(@PathVariable("id") UUID id){
        return service.getOne(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponseModel> update(@PathVariable("id") UUID id, @Valid @RequestBody UpdateStudentDto dto){
        return service.update(id, dto);
    }
}
