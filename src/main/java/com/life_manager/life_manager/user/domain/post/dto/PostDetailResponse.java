package com.life_manager.life_manager.user.domain.post.dto;

import com.life_manager.life_manager.user.domain.post.Post;
import com.life_manager.life_manager.user.domain.post.RecruitmentStatus;
import com.life_manager.life_manager.user.domain.post.WageType;
import lombok.AccessLevel;
import lombok.Builder;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record PostDetailResponse(
        Long id,
        String content,
        String title,
        BigDecimal wage,
        String address,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Time lunchStartTime,
        Time lunchEndTime,
        LocalDateTime createdAt,
        String category,
        Integer numberOfApplicants,
        String phoneNumber,
        Integer numberOfAccepted,
        RecruitmentStatus recruitmentStatus,
        String adminId,
        WageType wageType
) {
    public static PostDetailResponse from(Post post, int numberOfAccepted) {
        return builder()
                .id(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .wage(post.getWage())
                .address(post.getAddress())
                .lunchStartTime(post.getLunchStartTime())
                .lunchEndTime(post.getLunchEndTime())
                .createdAt(post.getCreatedAt())
                .numberOfAccepted(numberOfAccepted)
                .startTime(post.getStartTime())
                .endTime(post.getEndTime())
                .category(post.getCategory())
                .numberOfApplicants(post.getNumberOfApplicants())
                .phoneNumber(post.getPhoneNumber())
                .recruitmentStatus(post.getRecruitmentStatus())
                .adminId(post.getAdminId())
                .wageType(post.getWageType())
                .build();
    }
}