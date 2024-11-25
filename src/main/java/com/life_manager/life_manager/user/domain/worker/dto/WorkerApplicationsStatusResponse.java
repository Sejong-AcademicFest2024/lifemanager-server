package com.life_manager.life_manager.user.domain.worker.dto;

import com.life_manager.life_manager.user.domain.post.Post;
import com.life_manager.life_manager.user.domain.worker.relation.ApplicationStatus;
import com.life_manager.life_manager.user.domain.worker.relation.WorkerApplicationStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class WorkerApplicationsStatusResponse {
    private Long postId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
    private String address;
    private ApplicationStatus status;


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
