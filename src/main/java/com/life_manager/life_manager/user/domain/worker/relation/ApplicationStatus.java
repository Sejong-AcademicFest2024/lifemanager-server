package com.life_manager.life_manager.user.domain.worker.relation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApplicationStatus {
    PENDING("매칭 중"),
    ACCEPTED("수락됨"),
    EXPIRED("만료됨"),
    CANCEL("취소함"),
    REJECTED("거절됨");

    private final String description;
}