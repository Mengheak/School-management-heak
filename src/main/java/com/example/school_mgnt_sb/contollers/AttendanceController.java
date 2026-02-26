package com.example.school_mgnt_sb.contollers;


import com.example.school_mgnt_sb.dto.attendance.CreateAttendanceSessionDto;
import com.example.school_mgnt_sb.dto.attendance.UpsertAttendanceRecordsDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService service;

    @PostMapping("/sessions")
    public ResponseEntity<BaseResponseModel> createSession(@Valid @RequestBody CreateAttendanceSessionDto dto){
        return this.service.createOrGetSession(dto);
    }
    @GetMapping("/sessions/{sessionId}")
    public ResponseEntity<BaseResponseModel> getSession(@PathVariable("sessionId")UUID id){
        return this.service.getSession(id);
    }
    @PostMapping("/sessions/{sessionId}/records")
    public ResponseEntity<BaseResponseModel> upsertRecords (@PathVariable("sessionId") UUID id, @Valid @RequestBody UpsertAttendanceRecordsDto dto){
        return this.service.upsertRecords(id, dto);
    }
}
