package vn.edu.epu.quanlyhoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.epu.quanlyhoso.entity.Account;
import vn.edu.epu.quanlyhoso.entity.Lecturer;

import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    //Tìm hồ sơ dựa vào Account
    Optional<Lecturer> findByAccount(Account account);
}