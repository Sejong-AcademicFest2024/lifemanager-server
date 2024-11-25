package com.life_manager.life_manager.admin.domain.post.dto;

import com.life_manager.life_manager.admin.domain.post.Post;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
public class PostUpdateDto {

    private String content;
    private BigInteger wage;
    private String address;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String category;
    private Integer numberOfApplicants;
    private String phoneNumber;
    private Post.WageType wageType;
    private LocalDateTime recruitmentStartTime;
    private LocalDateTime recruitmentEndTime;
    private LocalTime lunchStartTime;
    private LocalTime lunchEndTime;
    private Integer numberOfRecruits;
    private Post.PostStatus postStatus;
    private String title;
    public PostDto toDto() {
        return new PostDto(
                content,
                wage,
                address,
                startTime,
                endTime,
                category,
                numberOfApplicants,
                phoneNumber,
                wageType,
                recruitmentStartTime,
                recruitmentEndTime,
                lunchStartTime,
                lunchEndTime,
                numberOfRecruits,
                postStatus,
                title
        );
    }
}