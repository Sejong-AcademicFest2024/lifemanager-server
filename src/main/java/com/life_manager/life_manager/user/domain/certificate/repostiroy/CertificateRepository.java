package com.life_manager.life_manager.user.domain.certificate.repostiroy;

import java.util.List;

import com.life_manager.life_manager.user.domain.certificate.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    List<Certificate> findAllByAffiliation(String affiliationName);

    @Query("SELECT DISTINCT c.affiliation FROM Certificate c")
    List<String> findAllAffiliation();
}
