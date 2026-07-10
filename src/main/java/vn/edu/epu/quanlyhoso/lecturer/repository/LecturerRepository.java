package vn.edu.epu.quanlyhoso.lecturer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.epu.quanlyhoso.auth.entity.Account;
import vn.edu.epu.quanlyhoso.lecturer.entity.*;

import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    //Tìm hồ sơ dựa vào Account
    Optional<Lecturer> findByAccount(Account account);
}