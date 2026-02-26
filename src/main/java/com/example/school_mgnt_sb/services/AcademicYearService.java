package com.example.school_mgnt_sb.services;


import com.example.school_mgnt_sb.dto.academic_year.AcademicYearResponse;
import com.example.school_mgnt_sb.dto.academic_year.CreateAcademicYearDto;
import com.example.school_mgnt_sb.entities.AcademicYear;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.AcademicYearMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.AcademicYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AcademicYearService {
    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private AcademicYearMapper academicYearMapper;

    public ResponseEntity<BaseResponseModel> createAcademicYear(CreateAcademicYearDto dto){
        validateDates(dto.startDate(), dto.endDate());
        if(academicYearRepository.existsByName(dto.name())){
            throw new StatusException(HttpStatus.CONFLICT, "Academic year name already exists: "+dto.name());
        }
        boolean active = dto.active() != null && dto.active();

        AcademicYear year = new AcademicYear();
        year.setName(dto.name());
        year.setStartDate(dto.startDate());
        year.setEndDate(dto.endDate());
        year.setActive(active);
        System.out.println(dto);
        AcademicYear savedAc = academicYearRepository.save(year);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "created academic year successfully", savedAc));
    }
    public ResponseEntity<BaseResponseModel> getById(UUID id){
        AcademicYear fetchedAcd = academicYearRepository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can not find item with id = " + id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get one academic year successfully", academicYearMapper.toResponse(fetchedAcd)));
    }

    public ResponseEntity<BaseResponseModel> listAc(){
        List<AcademicYear> academicYears = academicYearRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get all academic years successfully", academicYears.stream().map(academicYearMapper::toResponse).toList()));
    }


    private void validateDates(LocalDate start, LocalDate end){
        if(start.isAfter(end)){
            throw new StatusException(HttpStatus.BAD_REQUEST, "startDate must be before or equal to endDate");
        }
    }

}
