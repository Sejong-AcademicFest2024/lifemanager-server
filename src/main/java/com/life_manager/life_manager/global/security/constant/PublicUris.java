package com.life_manager.life_manager.global.security.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PublicUris {
    SIGN_IN("/auth/sign-in"),
    SIGN_UP("/worker/sign-up"),
    SEND_CODE("/auth/send-code"),
    VERIFY("/auth/verify"),
    SKILL_CATEGORY("/skill/category"),
    SKILL("/skill");

    private final String value;

    public static List<String> getAllUrisWithEndpointPrefix() {
        return Arrays.stream(PublicUris.values())
                .map(PublicUris::getUriWithEndpointPrefix)
                .collect(Collectors.toUnmodifiableList());
    }

    private String getUriWithEndpointPrefix() {
        return value;
    }
}
