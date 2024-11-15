package com.life_manager.life_manager.user.domain.resume.repository;

import com.life_manager.life_manager.user.domain.resume.ResumeCertificateRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeCertificateRelationRepository extends JpaRepository<ResumeCertificateRelation, ResumeCertificateRelation.ResumeCertificateRelationId> {
}
