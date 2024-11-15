package com.life_manager.life_manager.user.domain.auth.response;

public record VerificationResponse(Boolean isSuccess, String errorMessage) {
    public static VerificationResponse success() {
        return new VerificationResponse(true, null);
    }

    public static VerificationResponse fail(String errorMessage) {
        return new VerificationResponse(false, errorMessage);
    }
}