package com.life_manager.life_manager.user.domain.certificate.dto;


import com.life_manager.life_manager.user.domain.certificate.Certificate;

public record CertificateInfoResponse(Integer id, String affiliation, String item) {
    public static CertificateInfoResponse from(Certificate certificate) {
        return new CertificateInfoResponse(
                certificate.getId(),
                certificate.getAffiliation(),
                certificate.getItem()
        );
    }

}
