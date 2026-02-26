package com.example.school_mgnt_sb.repositories;

import com.example.school_mgnt_sb.dto.score.ScoreResponseDto;
import com.example.school_mgnt_sb.dto.score.UpsertScoreDto;
import com.example.school_mgnt_sb.entities.assessment.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ScoreRepository extends JpaRepository<Score, UUID> {
    Optional<Score> findByAssessment_IdAndStudent_Id(UUID assessmentId, UUID studentId);
    List<Score> findByStudent_Id(UUID studentId);
    List<Score> findByAssessment_Id(UUID assessmentId);
}
