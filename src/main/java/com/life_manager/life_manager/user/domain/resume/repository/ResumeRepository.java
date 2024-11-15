package com.life_manager.life_manager.user.domain.resume.repository;

import com.life_manager.life_manager.user.domain.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResumeRepository extends JpaRepository<Resume, String> {
}
