package vn.edu.epu.quanlyhoso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import vn.edu.epu.quanlyhoso.dto.request.ProfileRequest;
import vn.edu.epu.quanlyhoso.entity.Account;
import vn.edu.epu.quanlyhoso.entity.Lecturer;
import vn.edu.epu.quanlyhoso.repository.AccountRepository;
import vn.edu.epu.quanlyhoso.repository.LecturerRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final LecturerRepository lecturerRepository;
    private final AccountRepository accountRepository;

    private Account getCurrentAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy người dùng hiện tại!"));
    }

    public Lecturer getMyProfile() {
        Account currentAcc = getCurrentAccount();
        return lecturerRepository.findByAccount(currentAcc)
                .orElse(new Lecturer()); 
    }

    public Lecturer updateMyProfile(ProfileRequest request) {
        Account currentAcc = getCurrentAccount();
        
        Lecturer profile = lecturerRepository.findByAccount(currentAcc)
                .orElse(new Lecturer());

        // Đổ dữ liệu từ Request vào Entity 
        profile.setAccount(currentAcc);
        profile.setFullName(request.getFullName());
        profile.setAcademicRank(request.getAcademicRank());     
        profile.setAcademicDegree(request.getAcademicDegree()); 
        profile.setFaculty(request.getFaculty());               
        profile.setResearchDirection(request.getResearchDirection());

        return lecturerRepository.save(profile);
    }
}
