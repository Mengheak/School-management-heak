package com.example.school_mgnt_sb.services;


import com.example.school_mgnt_sb.dto.attendance.AttendanceRecordUpsertDto;
import com.example.school_mgnt_sb.dto.attendance.AttendanceSessionResponseDto;
import com.example.school_mgnt_sb.dto.attendance.CreateAttendanceSessionDto;
import com.example.school_mgnt_sb.dto.attendance.UpsertAttendanceRecordsDto;
import com.example.school_mgnt_sb.entities.Student;
import com.example.school_mgnt_sb.entities.attendance.AttendanceRecord;
import com.example.school_mgnt_sb.entities.attendance.AttendanceSession;
import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.AttendanceMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.AttendanceRecordRepository;
import com.example.school_mgnt_sb.repositories.AttendanceSessionRepository;
import com.example.school_mgnt_sb.repositories.StudentRepository;
import com.example.school_mgnt_sb.repositories.TeachingAssignmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttendanceService {


    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private AttendanceSessionRepository attendanceSessionRepository;


    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private TeachingAssignmentRepository teachingAssignmentRepository;


    @Autowired
    private AttendanceMapper mapper;

    public ResponseEntity<BaseResponseModel> createOrGetSession(CreateAttendanceSessionDto dto){
        TeachingAssignment teachingAssignment = teachingAssignmentRepository.findById(dto.getTeachingAssignmentId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Can't find teaching assignment with id = " + dto.getTeachingAssignmentId()));
        AttendanceSession attendanceSession = attendanceSessionRepository.findByTeachingAssignment_IdAndDate(teachingAssignment.getId(), dto.getDate())
                .orElseGet(() -> {
                    AttendanceSession attendanceSession1 = new AttendanceSession();
                    attendanceSession1.setTeachingAssignment(teachingAssignment);
                    attendanceSession1.setDate(dto.getDate());
                    attendanceSession1.setNote(dto.getNote());
                    return attendanceSessionRepository.save(attendanceSession1);
                });
        // If session existed and note provided, update note (optional behavior)
        if(dto.getNote() != null) {
            attendanceSession.setNote(dto.getNote());
            attendanceSessionRepository.save(attendanceSession);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseModel("success", "Created session successfully"));
    }

    @Transactional
    public ResponseEntity<BaseResponseModel> getSession(UUID id) {
        AttendanceSession fetched = attendanceSessionRepository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Can't get session with id = " + id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get session successfully", mapper.toResponseBasic(fetched)));
    }
    public ResponseEntity<BaseResponseModel> upsertRecords(UUID sessionId, UpsertAttendanceRecordsDto dto){
        AttendanceSession session = attendanceSessionRepository.findById(sessionId)
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Can't find session with Id = " + sessionId));

        // 1) No duplicate studentId
        Set<UUID> seen = new HashSet<>();
        for (AttendanceRecordUpsertDto r : dto.getRecords()) {
            if (!seen.add(r.getStudentId())) {
                throw new StatusException(HttpStatus.BAD_REQUEST, "Duplicate student id in request: " + r.getStudentId());
            }
        }

        // 2) Load students in bulk
        List<UUID> ids = dto.getRecords().stream()
                .map(AttendanceRecordUpsertDto::getStudentId)
                .toList();

        Map<UUID, Student> studentMap = studentRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(Student::getId, s -> s));

        if (studentMap.size() != ids.size()) {
            List<UUID> missingStudent = ids.stream().filter(id -> !studentMap.containsKey(id)).toList();
            throw new StatusException(HttpStatus.BAD_REQUEST, "Missing students: " + missingStudent);
        }

        // 3) Upsert records
        List<AttendanceRecord> saved = new ArrayList<>();

        for (AttendanceRecordUpsertDto r: dto.getRecords()){
            Student student = studentMap.get(r.getStudentId());

            AttendanceRecord record = attendanceRecordRepository
                    .findBySession_IdAndStudent_Id(sessionId, student.getId())
                    .orElseGet(() -> {
                        AttendanceRecord newRec = new AttendanceRecord();
                        newRec.setSession(session);
                        newRec.setStudent(student);
                        return newRec;
                    });

            record.setStatus(r.getStatus());
            record.setRemark(r.getRemark());

            saved.add(attendanceRecordRepository.save(record));
        }

        session.setRecords(attendanceRecordRepository.findBySession_Id(sessionId));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseModel<>("success", "upsert records successfully", mapper.toResponseWithRecords(session)));
    }
}
