package vn.edu.epu.quanlyhoso.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.edu.epu.quanlyhoso.admin.service.AdminService;
import vn.edu.epu.quanlyhoso.auth.dto.request.UpdateRoleRequest;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PutMapping("/assign-role")
    public ResponseEntity<?> assignRole(@RequestBody UpdateRoleRequest request) {
        try {
            String message = adminService.assignRole(request.getUsername(), request.getRoleId());
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
