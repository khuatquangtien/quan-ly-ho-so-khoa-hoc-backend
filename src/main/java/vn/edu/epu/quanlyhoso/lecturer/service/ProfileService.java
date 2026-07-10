package vn.edu.epu.quanlyhoso.lecturer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.edu.epu.quanlyhoso.auth.repository.*;
import vn.edu.epu.quanlyhoso.auth.entity.Account;
import vn.edu.epu.quanlyhoso.lecturer.repository.*;
import vn.edu.epu.quanlyhoso.lecturer.entity.*;
import vn.edu.epu.quanlyhoso.lecturer.dto.*;




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
