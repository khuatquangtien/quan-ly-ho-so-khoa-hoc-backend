package vn.edu.epu.quanlyhoso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.edu.epu.quanlyhoso.dto.request.ProfileRequest;
import vn.edu.epu.quanlyhoso.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // Xem thông tin cá nhân
    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile() {
        try {
            return ResponseEntity.ok(profileService.getMyProfile());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cập nhật thông tin cá nhân
    @PutMapping("/me")
    public ResponseEntity<?> updateMyProfile(@RequestBody ProfileRequest request) {
        try {
            return ResponseEntity.ok(profileService.updateMyProfile(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
