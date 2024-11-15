package com.life_manager.life_manager.user.domain.certificate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CertificateType {
    S("국가전문자격"),
    T("국가기술자격");

    private final String value;
}
