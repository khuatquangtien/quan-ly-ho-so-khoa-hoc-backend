package vn.edu.epu.quanlyhoso.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.epu.quanlyhoso.entity.ProjectSubmission;

public interface ProjectSubmissionRepository extends JpaRepository<ProjectSubmission, Integer> {

    Optional<ProjectSubmission> findByProjectId(Integer projectId);

    boolean existsByProjectId(Integer projectId);
}
