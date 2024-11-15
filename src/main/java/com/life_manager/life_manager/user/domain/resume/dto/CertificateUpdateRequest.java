package com.life_manager.life_manager.user.domain.resume.dto;

import java.util.List;

public record CertificateUpdateRequest(
        List<Integer> ids
) {
}
