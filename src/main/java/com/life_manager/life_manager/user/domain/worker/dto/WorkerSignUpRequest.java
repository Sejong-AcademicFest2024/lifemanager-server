package com.life_manager.life_manager.user.domain.worker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record WorkerSignUpRequest(
        @NotBlank(message = EMPTY_MEMBER_ACCOUNT)
        @Pattern(regexp = MEMBER_ACCOUNT_REGEX, message = INVALID_MEMBER_ACCOUNT)
        String memberAccount,
        @NotBlank(message = EMPTY_PASSWORD)
        @Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD)
        String password,
        @NotBlank(message = EMPTY_PASSWORD_CHECK)
        @Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD)
        String passwordCheck,
        @NotBlank(message = EMPTY_NAME)
        String name,
        @NotBlank(message = EMPTY_PHONE_NUMBER)
        @Pattern(regexp = PHONE_NUMBER_REGEX, message = INVALID_PHONE_NUMBER)
        String phoneNumber,
        @NotBlank(message = EMPTY_BIRTHDATE)
        LocalDate birthdate,
        @NotBlank(message = EMPTY_NICKNAME)
        @Pattern(regexp = NICKNAME_REGEX, message = INVALID_NICKNAME)
        String nickname,
        @NotNull
        Gender gender,
        @NotBlank(message = EMPTY_ADDRESS)
        String address,
        @NotNull
        Integer skillId
) {
}