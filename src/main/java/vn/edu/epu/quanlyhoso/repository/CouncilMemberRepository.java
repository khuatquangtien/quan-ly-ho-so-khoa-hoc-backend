package vn.edu.epu.quanlyhoso.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.epu.quanlyhoso.entity.CouncilMember;
import vn.edu.epu.quanlyhoso.entity.CouncilMemberId;

public interface CouncilMemberRepository extends JpaRepository<CouncilMember, CouncilMemberId> {
    List<CouncilMember> findByCouncilId(Long councilId);
}
