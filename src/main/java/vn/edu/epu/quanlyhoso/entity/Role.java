package vn.edu.epu.quanlyhoso.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Integer id;
	 
	@Column(name = "role_name", nullable = false, unique = true)
	private String roleName;
	
	@OneToMany(mappedBy = "role")
	private List<Account> accounts = new ArrayList<>();
	
}