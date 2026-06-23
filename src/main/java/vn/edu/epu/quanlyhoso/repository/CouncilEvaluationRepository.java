package vn.edu.epu.quanlyhoso.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.epu.quanlyhoso.entity.CouncilEvaluation;

public interface CouncilEvaluationRepository extends JpaRepository<CouncilEvaluation, Long> {
    Optional<CouncilEvaluation> findByProjectId(Long projectId);
}
