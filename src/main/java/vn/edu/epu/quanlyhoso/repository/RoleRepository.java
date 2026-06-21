package vn.edu.epu.quanlyhoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.epu.quanlyhoso.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //java tự mình điền vào hàm này
}