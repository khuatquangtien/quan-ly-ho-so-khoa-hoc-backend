package vn.edu.epu.quanlyhoso.lecturer;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.epu.quanlyhoso.auth.Account;
import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    //Tìm hồ sơ dựa vào Account
    Optional<Lecturer> findByAccount(Account account);
}