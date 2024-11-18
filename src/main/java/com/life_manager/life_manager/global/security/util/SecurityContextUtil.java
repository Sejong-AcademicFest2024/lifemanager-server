package com.life_manager.life_manager.global.security.util;

import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityContextUtil {
    public static String extractWorkerId() {
        return (String)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
