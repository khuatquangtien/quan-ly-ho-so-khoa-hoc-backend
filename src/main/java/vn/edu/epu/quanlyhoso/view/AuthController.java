package vn.edu.epu.quanlyhoso.view;

import jakarta.validation.Valid; // Nhớ import thư viện này
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.epu.quanlyhoso.model.dto.AuthRequest;
import vn.edu.epu.quanlyhoso.model.dto.AuthResponse;
import vn.edu.epu.quanlyhoso.model.dto.RegisterRequest;
import vn.edu.epu.quanlyhoso.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    
    @PostMapping("/register")
    // Thêm @Valid vào đây để chặn cổng ngay từ đầu!
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (RuntimeException e) {
            // Bắt lỗi trùng username hoặc lỗi nghiệp vụ từ Service
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
