package com.life_manager.life_manager.user.domain.worker.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record WorkerApplicationsStatusResponse(
        Long postId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String title,
        String address,
        ApplicationStatus status
) {
    public static WorkerApplicationsStatusResponse from(WorkerApplicationStatus workerApplicationStatus) {
        Post post = workerApplicationStatus.getPost();

        return WorkerApplicationsStatusResponse.builder()
                .postId(post.getId())
                .startTime(post.getStartTime())
                .endTime(post.getEndTime())
                .title(post.getTitle())
                .address(post.getAddress())
                .status(workerApplicationStatus.getStatus())
                .build();
    }
}