package vn.edu.epu.quanlyhoso.model.dto;

import lombok.Data;

@Data
public class UpdateRoleRequest {
    private String username; 
    private Integer roleId;
}
