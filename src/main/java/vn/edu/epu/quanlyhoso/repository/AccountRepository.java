package vn.edu.epu.quanlyhoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.epu.quanlyhoso.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    //Tìm hồ sơ dựa vào username
    Optional<Account> findByUsername(String username);
}