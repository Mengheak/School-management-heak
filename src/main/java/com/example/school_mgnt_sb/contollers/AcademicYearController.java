package com.example.school_mgnt_sb.contollers;


import com.example.school_mgnt_sb.dto.academic_year.CreateAcademicYearDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.AcademicYearService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/academic-years")
public class AcademicYearController {
    @Autowired
    private AcademicYearService academicYearService;


    @GetMapping
    public ResponseEntity<BaseResponseModel> listAc(){
        return this.academicYearService.listAc();
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> create(@RequestBody @Valid CreateAcademicYearDto dto){
        return this.academicYearService.createAcademicYear(dto);
    }
}
