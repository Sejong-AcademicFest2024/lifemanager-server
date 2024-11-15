package com.life_manager.life_manager.user.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerificationCodeMessageRequest(
        @NotBlank(message = "EMPTY_PHONE_NUMBER")
        @Pattern(regexp = "PHONE_NUMBER_REGEX", message = "INVALID_PHONE_NUMBER")
        String receiver) {
}