package vn.edu.epu.quanlyhoso.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.epu.quanlyhoso.entity.Account;
import vn.edu.epu.quanlyhoso.entity.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

    Optional<Lecturer> findByAccountId(Integer accountId);
    
    Optional<Lecturer> findByAccount(Account account);
    
    List<Lecturer> findByFaculty(String faculty);
}

