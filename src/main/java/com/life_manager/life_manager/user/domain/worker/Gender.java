package com.life_manager.life_manager.user.domain.worker;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String name;
}