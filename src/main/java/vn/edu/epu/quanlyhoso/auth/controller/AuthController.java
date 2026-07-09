package vn.edu.epu.quanlyhoso.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.epu.quanlyhoso.auth.dto.request.AuthRequest;
import vn.edu.epu.quanlyhoso.auth.dto.request.RegisterRequest;
import vn.edu.epu.quanlyhoso.auth.dto.response.AuthResponse;
import vn.edu.epu.quanlyhoso.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // Hứng request từ Frontend -> Quăng cho Service xử lý -> Trả HTTP 200 (OK) và Token về
        return ResponseEntity.ok(authService.authenticate(request));
    }
    // Thêm API này vào dưới API login hiện tại của em
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (RuntimeException e) {
        // Bắt lỗi trùng username hoặc sai Role để vả lại lỗi 400 Bad Request
            return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}
