package com.example.school_mgnt_sb.contollers;


import com.example.school_mgnt_sb.dto.assessment.CreateAssessmentDto;
import com.example.school_mgnt_sb.dto.assessment.UpdateAssessmentDto;
import com.example.school_mgnt_sb.dto.score.UpsertScoreDto;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.AssessmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assessments")
public class AssessmentController {
    @Autowired
    private AssessmentService service;

    @PostMapping
    public ResponseEntity<BaseResponseModel> create (@Valid @RequestBody CreateAssessmentDto dto){
        return this.service.create(dto);
    }
    @GetMapping
    public ResponseEntity<BaseResponseModel> list(){
        return this.service.list();

    }
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponseModel> update(@PathVariable("id")UUID id, @Valid @RequestBody UpdateAssessmentDto dto) {
        return this.service.update(id, dto);
    }

    @GetMapping("/{assessmentId}/scores")
    public ResponseEntity<BaseResponseModel> listScores(@PathVariable("assessmentId") UUID assessmentId){
        return this.service.listScores(assessmentId);
    }

    @PostMapping("/{assessmentId}/scores")
    public ResponseEntity<BaseResponseModel> upsertScores(@PathVariable("assessmentId") UUID assessmentId, @Valid @RequestBody UpsertScoreDto req){
        return this.service.upsertScores(assessmentId, req);
    }
}
