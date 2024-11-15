package com.life_manager.life_manager.user.domain.auth.dto;

public record VerificationCodeMessageResponse(Boolean isSuccess, String errorMessage) {
    public static VerificationCodeMessageResponse success() {
        return new VerificationCodeMessageResponse(true, null);
    }

    public static VerificationCodeMessageResponse fail(String errorMessage) {
        return new VerificationCodeMessageResponse(false, errorMessage);
    }
}