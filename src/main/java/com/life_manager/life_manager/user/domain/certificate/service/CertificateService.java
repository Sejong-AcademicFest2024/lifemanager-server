package com.life_manager.life_manager.user.domain.certificate.service;

import com.life_manager.life_manager.user.domain.certificate.Certificate;
import com.life_manager.life_manager.user.domain.certificate.dto.CertificateAffiliationResponse;
import com.life_manager.life_manager.user.domain.certificate.dto.CertificateInfoResponse;
import com.life_manager.life_manager.user.domain.certificate.repostiroy.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificateService {
    private final CertificateRepository certificateRepository;

    @Transactional(readOnly = true)
    public List<CertificateInfoResponse> getCertificateByAffiliation(String affiliationName) {
        List<Certificate> certificationList = certificateRepository.findAllByAffiliation(affiliationName);

        return certificationList.stream()
                .map(CertificateInfoResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public CertificateAffiliationResponse getCertificateAffiliation() {
        List<String> affiliations = certificateRepository.findAllAffiliation();

        return new CertificateAffiliationResponse(affiliations);
    }
}