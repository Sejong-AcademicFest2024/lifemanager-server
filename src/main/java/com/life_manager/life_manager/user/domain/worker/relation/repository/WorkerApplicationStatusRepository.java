package com.life_manager.life_manager.user.domain.worker.relation.repository;

import java.util.List;

import com.life_manager.life_manager.user.domain.worker.relation.WorkerApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerApplicationStatusRepository extends JpaRepository<WorkerApplicationStatus, Long> {
    List<WorkerApplicationStatus> findAllByPost(Post post);
}
