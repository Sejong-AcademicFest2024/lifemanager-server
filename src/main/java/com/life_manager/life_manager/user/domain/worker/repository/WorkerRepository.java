package com.life_manager.life_manager.user.domain.worker.repository;

import java.util.Optional;

import com.life_manager.life_manager.user.domain.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkerRepository extends JpaRepository<Worker, String> {
    boolean existsByMemberAccount(String memberAccount);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNickname(String nickname);

    Optional<Worker> findByMemberAccount(String memberAccount);
}