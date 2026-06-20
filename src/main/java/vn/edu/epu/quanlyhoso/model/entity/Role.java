package vn.edu.epu.quanlyhoso.model.entity;

import jakarta.persistence.*;
import lombok.Data;
//Tạo sẵn bảng Role để java nhận biết dưới database có những gì
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;
}