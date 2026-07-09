package vn.edu.epu.quanlyhoso.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.epu.quanlyhoso.admin.dto.request.UpdateAccountRequest;
import vn.edu.epu.quanlyhoso.admin.service.AdminService;
import vn.edu.epu.quanlyhoso.auth.dto.request.UpdateRoleRequest;
import vn.edu.epu.quanlyhoso.auth.dto.response.AccountResponse;

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
    
    //xoa tài khoản vai trò admin
    
    @DeleteMapping("/{id}")
    public void DeleteAccount(@PathVariable Integer id) {
    	adminService.deleteById(id);
    }
    // lấy tài khoản tất cả người dùng
    @GetMapping("/users")
    public List<AccountResponse> GetAllAccount(){
    	return adminService.GetAllAccount();
    }
    
    // sửa tất cả 
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Integer id, @RequestBody UpdateAccountRequest request  ){
    	try {
			adminService.updateAccount(id, request);
			return ResponseEntity.ok("Đã cập nhật tài khoản");
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
}
