package com.example.school_mgnt_sb.services;

import com.example.school_mgnt_sb.common.enums.EnrollmentStatus;
import com.example.school_mgnt_sb.dto.assessment.CreateAssessmentDto;
import com.example.school_mgnt_sb.dto.assessment.UpdateAssessmentDto;
import com.example.school_mgnt_sb.dto.score.ScoreUpsertDto;
import com.example.school_mgnt_sb.dto.score.UpsertScoreDto;
import com.example.school_mgnt_sb.entities.Student;
import com.example.school_mgnt_sb.entities.assessment.Assessment;
import com.example.school_mgnt_sb.entities.assessment.Score;
import com.example.school_mgnt_sb.entities.relationship.Enrollment;
import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.AssessmentMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssessmentService {
    @Autowired
    private AssessmentMapper mapper;

    @Autowired
    private TeachingAssignmentRepository teachingAssignmentRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public ResponseEntity<BaseResponseModel> create(CreateAssessmentDto dto){
        TeachingAssignment teachingAssignment = teachingAssignmentRepository.findById(dto.getTeachingAssignmentId())
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Can't find teaching assignment with id = " + dto.getTeachingAssignmentId()));
        Assessment assessment = mapper.toEntity(dto, teachingAssignment);
        Assessment saved = assessmentRepository.save(assessment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "created assessment successfully", mapper.toResponse(saved)));
    }

    public ResponseEntity<BaseResponseModel> list(){
        List<Assessment> fetched = assessmentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success","list all assessments successfully", fetched.stream().map(mapper::toResponse).toList()));
    }

    public ResponseEntity<BaseResponseModel> update(UUID id, UpdateAssessmentDto dto){
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "can't find assessment with id = " + id));
        mapper.updateEntity(dto, assessment);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseModel("success", "updated assessment successfully"));
    }


    public ResponseEntity<BaseResponseModel> listScores(UUID assessmentId){
        if(!assessmentRepository.existsById(assessmentId)){
            throw new StatusException(HttpStatus.NOT_FOUND, "Assessment not found: "+ assessmentId);
        }
       List<Score> fetched =  scoreRepository.findByAssessment_Id(assessmentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "List scores of assessmentId = " + assessmentId+ " successfully", fetched.stream().map(mapper::toScoreResponse)));
    }
//    public ResponseEntity<BaseResponseModel> upsertScores(UUID assessmentId, UpsertScoreDto dto){
//        Assessment assessment = assessmentRepository.findById(assessmentId)
//                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Can't find assessment with id = " + assessmentId));
//        //validate no duplicate input for student id
//        Set<UUID> seen = new HashSet<>();
//        for(var s: dto.getScores()){
//            if(!seen.add(s.getStudentId())){
//                throw  new StatusException(HttpStatus.BAD_REQUEST, "Duplicate studentId in request "+ s.getStudentId());
//            }
//        }
//
//        List<UUID> studentIds = dto.getScores().stream().map(ScoreUpsertDto::getStudentId).toList();
//        Map<UUID, Student> studentMap = studentRepository.findAllById(studentIds).stream().collect(Collectors.toMap(Student::getId, s->s));
//
//
//        //check if there are missing studentIds in studentMap
//        if(studentIds.size() != studentMap.size()){
//            List<UUID> missingIds = studentIds.stream().filter(id -> !studentMap.containsKey(id)).toList();
//            throw new StatusException(HttpStatus.BAD_REQUEST, "Some studentIds not found: " + missingIds);
//        }
//
//        //Enrollment validation check(ACTIVE In same classroom/year)
//        UUID classroomId = assessment.getTeachingAssignment().getClassroom().getId();
//        UUID yearId = assessment.getTeachingAssignment().getAcademicYear().getId();
//
//        List<Enrollment> enrolled = enrollmentRepository.findByAcademicYear_IdAndClassroom_IdAndStatusAndStudent_IdIn(
//                yearId, classroomId, EnrollmentStatus.ACTIVE, studentIds
//        );
//
//        Set<UUID> enrolledIds = enrolled.stream().map(e -> e.getStudent().getId()).collect(Collectors.toSet());
//        if(enrolledIds.size()!=studentIds.size()){
//            List<UUID> notEnrolledIds = studentIds.stream().filter(s -> !enrolledIds.contains(s)).toList();
//            throw new StatusException(HttpStatus.BAD_REQUEST, "Some students are not ACTIVE enrolled in this class/year " + notEnrolledIds);
//        }
//        double max = assessment.getMaxScore();
//        List<Score> saved = new ArrayList<>();
//
//        for(ScoreUpsertDto s: dto.getScores()){
//            if(s.getValue() < 0 || s.getValue() > max){
//                throw new StatusException(HttpStatus.BAD_REQUEST, "Score value must be between 0 and max score " + max);
//                Student student = studentMap.get(s.getStudentId());
//                scoreRepository.findByAssessment_IdAndStudent_Id(assessmentId, student.getId())
//                        .orElseGet(() -> Score.builder()
//                                .assessment(assessment)
//                                .student(student).
//                                build());
//                Score score;
//                score.setValue(s.getValue());
//                score.setRemark(s.getRemark());
//
//                saved.add(score);
//            }
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "Upsert scores to studentId = " + assessmentId + " successfully", saved.stream().map(mapper::toScoreResponse)));
//    }


    public ResponseEntity<BaseResponseModel> upsertScores(UUID assessmentId, UpsertScoreDto dto) {

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND,
                        "Can't find assessment with id = " + assessmentId));

        // 1) validate no duplicate studentId
        Set<UUID> seen = new HashSet<>();
        for (ScoreUpsertDto s : dto.getScores()) {
            if (!seen.add(s.getStudentId())) {
                throw new StatusException(HttpStatus.BAD_REQUEST,
                        "Duplicate studentId in request: " + s.getStudentId());
            }
        }

        // 2) load students in bulk
        List<UUID> studentIds = dto.getScores().stream()
                .map(ScoreUpsertDto::getStudentId)
                .toList();

        Map<UUID, Student> studentMap = studentRepository.findAllById(studentIds).stream()
                .collect(Collectors.toMap(Student::getId, st -> st));

        // ✅ correct missing list
        if (studentMap.size() != studentIds.size()) {
            List<UUID> missingIds = studentIds.stream()
                    .filter(id -> !studentMap.containsKey(id))
                    .toList();
            throw new StatusException(HttpStatus.BAD_REQUEST,
                    "Some studentIds not found: " + missingIds);
        }

        // 3) enrollment validation (ACTIVE in same classroom/year)
        UUID classroomId = assessment.getTeachingAssignment().getClassroom().getId();
        UUID yearId = assessment.getTeachingAssignment().getAcademicYear().getId();

        System.out.println(classroomId.toString() +"  "+ yearId.toString());


        List<Enrollment> enrolled = enrollmentRepository
                .findByAcademicYear_IdAndClassroom_IdAndStatusAndStudent_IdIn(
                        yearId, classroomId, EnrollmentStatus.ACTIVE, studentIds
                );


        Set<UUID> enrolledIds = enrolled.stream()
                .map(e -> e.getStudent().getId())
                .collect(Collectors.toSet());

        if (enrolledIds.size() != studentIds.size()) {
            List<UUID> notEnrolledIds = studentIds.stream()
                    .filter(id -> !enrolledIds.contains(id))
                    .toList();
            throw new StatusException(HttpStatus.BAD_REQUEST,
                    "Some students are not ACTIVE enrolled in this class/year: " + notEnrolledIds);
        }

        // 4) upsert scores
        double max = assessment.getMaxScore();
        List<Score> saved = new ArrayList<>();

        for (ScoreUpsertDto s : dto.getScores()) {

            if (s.getValue() < 0 || s.getValue() > max) {
                throw new StatusException(HttpStatus.BAD_REQUEST,
                        "Score value must be between 0 and maxScore (" + max + "). studentId=" + s.getStudentId());
            }

            Student student = studentMap.get(s.getStudentId());

            Score score = scoreRepository.findByAssessment_IdAndStudent_Id(assessmentId, student.getId())
                    .orElseGet(() -> Score.builder()
                            .assessment(assessment)
                            .student(student)
                            .build());

            score.setValue(s.getValue());
            score.setRemark(s.getRemark());

            saved.add(scoreRepository.save(score));
        }

        var data = saved.stream().map(mapper::toScoreResponse).toList();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseModel<>("success",
                        "Upsert scores successfully for assessmentId = " + assessmentId,
                        data));
    }

}
