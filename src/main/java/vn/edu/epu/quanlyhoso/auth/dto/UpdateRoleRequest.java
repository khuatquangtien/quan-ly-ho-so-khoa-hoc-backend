package vn.edu.epu.quanlyhoso.auth.dto;

import lombok.Data;

@Data
public class UpdateRoleRequest {
    private String username; 
    private Integer roleId;
}
