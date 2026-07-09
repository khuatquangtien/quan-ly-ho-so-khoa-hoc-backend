package vn.edu.epu.quanlyhoso.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    //Tìm hồ sơ dựa vào username
    Optional<Account> findByUsername(String username);
}