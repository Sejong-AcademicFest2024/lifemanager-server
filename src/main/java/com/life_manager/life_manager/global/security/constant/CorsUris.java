package com.life_manager.life_manager.global.security.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CorsUris {
    LOCAL_SERVER_URL("http://localhost:8080"),
    LOCAL_DOMAIN_URL("http://localhost:3000"),
    FRONTEND_URL("https://zigtong.vercel.app");

    private final String value;

    public static List<String> getAllUris() {
        return Arrays.stream(CorsUris.values())
                .map(CorsUris::getUri)
                .collect(Collectors.toUnmodifiableList());
    }

    private String getUri() {
        return value;
    }
}
