package com.example.school_mgnt_sb.mapper;


import com.example.school_mgnt_sb.dto.assessment.AssessmentResponseDto;
import com.example.school_mgnt_sb.dto.assessment.CreateAssessmentDto;
import com.example.school_mgnt_sb.dto.assessment.UpdateAssessmentDto;
import com.example.school_mgnt_sb.dto.score.ScoreResponseDto;
import com.example.school_mgnt_sb.entities.assessment.Assessment;
import com.example.school_mgnt_sb.entities.assessment.Score;
import com.example.school_mgnt_sb.entities.relationship.TeachingAssignment;
import org.springframework.stereotype.Component;

@Component
public class AssessmentMapper {

    public Assessment toEntity(
            CreateAssessmentDto dto,
            TeachingAssignment teachingAssignment
    ) {
        Assessment assessment = new Assessment();
        assessment.setTeachingAssignment(teachingAssignment);
        assessment.setType(dto.getType());
        assessment.setTitle(dto.getTitle());
        assessment.setDate(dto.getDate());

        if (dto.getMaxScore() != null) {
            assessment.setMaxScore(dto.getMaxScore());
        }

        assessment.setWeight(dto.getWeight());

        return assessment;
    }


    public void updateEntity(UpdateAssessmentDto dto, Assessment assessment) {

        if (dto.getType() != null) {
            assessment.setType(dto.getType());
        }

        if (dto.getTitle() != null) {
            assessment.setTitle(dto.getTitle());
        }

        if (dto.getDate() != null) {
            assessment.setDate(dto.getDate());
        }

        if (dto.getMaxScore() != null) {
            assessment.setMaxScore(dto.getMaxScore());
        }

        if (dto.getWeight() != null) {
            assessment.setWeight(dto.getWeight());
        }
    }

    public AssessmentResponseDto toResponse(Assessment assessment) {
        if (assessment == null) {
            return null;
        }

        AssessmentResponseDto dto = new AssessmentResponseDto();

        dto.setId(assessment.getId());

        if (assessment.getTeachingAssignment() != null) {
            dto.setTeachingAssignmentId(
                    assessment.getTeachingAssignment().getId()
            );
        }

        dto.setType(assessment.getType());
        dto.setTitle(assessment.getTitle());
        dto.setDate(assessment.getDate());
        dto.setMaxScore(assessment.getMaxScore());
        dto.setWeight(assessment.getWeight());

        return dto;
    }

    public ScoreResponseDto toScoreResponse(Score s){
        return new ScoreResponseDto(
                s.getId(),
                s.getStudent().getId(),
                s.getStudent().getFullName(),
                s.getStudent().getStudentCode(),
                s.getValue(),
                s.getRemark()
        );
    }
}
