package com.life_manager.life_manager.user.domain.resume.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CareerUpdateRequest(
        @NotBlank
        String companyName,
        @NotBlank
        String role,
        @NotBlank
        String roleDetail,
        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate
) {
}
