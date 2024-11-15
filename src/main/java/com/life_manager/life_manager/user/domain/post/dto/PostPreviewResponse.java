package com.life_manager.life_manager.user.domain.post.dto;

import com.life_manager.life_manager.user.domain.post.Post;
import com.life_manager.life_manager.user.domain.post.RecruitmentStatus;
import com.life_manager.life_manager.user.domain.post.WageType;
import lombok.AccessLevel;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record PostPreviewResponse(
        Long id,
        String title,
        WageType wageType,
        BigDecimal wage,
        RecruitmentStatus recruitmentStatus,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String category,
        String location
) {
    public static PostPreviewResponse from(Post post) {
        return PostPreviewResponse.builder()
                .id(post.getId())
                //.title(post.getTitle())
                .title(post.getTitle())
                .wageType(post.getWageType())
                .wage(post.getWage())
                .recruitmentStatus(post.getRecruitmentStatus())
                .location(post.getAddress())
                .startTime(post.getStartTime())
                .endTime(post.getEndTime())
                .category(post.getCategory())
                .build();
    }
}
