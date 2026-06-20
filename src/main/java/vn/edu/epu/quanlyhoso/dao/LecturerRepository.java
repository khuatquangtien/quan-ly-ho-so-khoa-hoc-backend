package vn.edu.epu.quanlyhoso.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.epu.quanlyhoso.model.entity.Lecturer;
import vn.edu.epu.quanlyhoso.model.entity.Account;
import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    //Tìm hồ sơ dựa vào Account
    Optional<Lecturer> findByAccount(Account account);
}