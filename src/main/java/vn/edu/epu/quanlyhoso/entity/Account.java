package vn.edu.epu.quanlyhoso.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//
    
    @Column(nullable = false, unique = true, length = 50)
    private String username;//

    @Column(nullable = false, length = 255)
    private String password;//

    @Column(nullable = false, unique = true, length = 100)
    private String email;//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;//

    @Column(name = "is_active")
    private Boolean isActive = true;//

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private Lecturer lecturer;

    public Collection<? extends GrantedAuthority> getAuthorities(){
    	if(this.role == null || this.role.getRoleName() == null) {
    		return List.of();
    	}
    	return List.of(new SimpleGrantedAuthority(this.role.getRoleName()));
    }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
    
}
