package vn.edu.epu.quanlyhoso.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    // Cho phép truyền roleId (Ví dụ: 1 cho Admin, 2 cho Giảng viên). 
    private Integer roleId;
}
