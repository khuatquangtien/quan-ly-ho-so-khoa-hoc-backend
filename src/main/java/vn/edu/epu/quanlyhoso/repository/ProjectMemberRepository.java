package vn.edu.epu.quanlyhoso.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.epu.quanlyhoso.entity.ProjectMember;
import vn.edu.epu.quanlyhoso.entity.ProjectMemberId;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByProject_Id(Integer projectId);

    List<ProjectMember> findByLecturer_Id(Integer lecturerId);

    boolean existsByProject_IdAndLecturer_Id(Integer projectId, Integer lecturerId);
}
