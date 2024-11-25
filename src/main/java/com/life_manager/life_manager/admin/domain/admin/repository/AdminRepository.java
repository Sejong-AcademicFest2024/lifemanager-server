package com.life_manager.life_manager.admin.domain.admin.repository;

import com.life_manager.life_manager.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
    boolean existsByAccountId(String accountId);
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Admin> findByAccountId(String accountId);
}
