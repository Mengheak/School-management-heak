package com.example.school_mgnt_sb.services;


import com.example.school_mgnt_sb.dto.teaching_assignment.CreateTeachingAssignmentDto;
import com.example.school_mgnt_sb.entities.Classroom;
import com.example.school_mgnt_sb.entities.Subject;
import com.example.school_mgnt_sb.entities.Teacher;
import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.TeachingAssignmentMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.ClassroomRepository;
import com.example.school_mgnt_sb.repositories.SubjectRepository;
import com.example.school_mgnt_sb.repositories.TeacherRepository;
import com.example.school_mgnt_sb.repositories.TeachingAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeachingAssignmentService {
    @Autowired
    private TeachingAssignmentRepository repository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    @Autowired
    private TeachingAssignmentMapper mapper;

    public ResponseEntity<BaseResponseModel> create(CreateTeachingAssignmentDto req){
        Classroom classroom = classroomRepository.findById(req.getClassroomId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Classroom not found: " + req.getClassroomId()));

        Subject subject = subjectRepository.findById(req.getSubjectId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Subject not found: " + req.getSubjectId()));

        Teacher teacher = teacherRepository.findById(req.getTeacherId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Teacher not found: " + req.getTeacherId()));

        UUID yearId = classroom.getAcademicYear().getId();

        if(repository.existsByAcademicYear_IdAndClassroom_IdAndSubject_IdAndTeacher_Id(yearId, classroom.getId(), subject.getId(), teacher.getId())){
            throw new StatusException(HttpStatus.BAD_REQUEST, "Teaching assignment already exists for this year/class/subject/teacher");
        }



        TeachingAssignment saved = repository.save(mapper.toEntity(req, subject, teacher, classroom));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "created assignment successfully", mapper.toResponse(saved)));
    }

    public ResponseEntity<BaseResponseModel> list() {
        List<TeachingAssignment> fetched = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get teaching assignment successfully", fetched.stream().map(mapper::toResponse)));
    }
}
