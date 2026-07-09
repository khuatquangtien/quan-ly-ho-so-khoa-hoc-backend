package vn.edu.epu.quanlyhoso.lecture.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import vn.edu.epu.quanlyhoso.auth.entity.Account;
import vn.edu.epu.quanlyhoso.project.entity.Project;
import vn.edu.epu.quanlyhoso.project.entity.ProjectMember;

@Data
@Entity
@Table(name = "lecturers")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Sử dụng FetchType.LAZY từ nhánh feature để tối ưu hiệu năng (chỉ load Account khi cần)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    // Giữ tên cột tường minh và thêm giới hạn độ dài 100 ký tự từ nhánh feature
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "academic_rank", length = 50) 
    private String academicRank;

    @Column(name = "academic_degree", length = 50) 
    private String academicDegree;

    @Column(name = "faculty", nullable = false, length = 100) 
    private String faculty;

    // Chọn columnDefinition = "TEXT" từ nhánh feature vì hướng nghiên cứu có thể viết rất dài, VARCHAR(255) sẽ bị thiếu
    @Column(name = "research_direction", columnDefinition = "TEXT") 
    private String researchDirection;

    // Ưu tiên dùng LocalDateTime (Java 8+) thay vì java.sql.Timestamp (kiểu cũ) để đồng bộ chuẩn Spring Boot hiện đại
    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    // ==========================================
    // CÁC MỐI QUAN HỆ QUẢN LÝ ĐỀ TÀI (Bắt buộc giữ từ nhánh feature/project-management)
    // ==========================================
    @OneToMany(mappedBy = "leader")
    private List<Project> ledProjects = new ArrayList<>();

    @OneToMany(mappedBy = "lecturer")
    private List<ProjectMember> projectMemberships = new ArrayList<>();

}