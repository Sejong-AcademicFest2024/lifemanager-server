package com.life_manager.life_manager.admin.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.life_manager.life_manager.admin.domain.post.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
public class PostCreateDto {

    @NotNull
    private String content;
    @NotNull
    private BigInteger wage;
    @NotNull
    private String address;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private String category;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Post.WageType wageType;
    @NotNull
    private LocalDateTime recruitmentStartTime;
    @NotNull

    private LocalDateTime recruitmentEndTime;
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lunchStartTime;
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lunchEndTime;
    @NotNull
    private Integer numberOfRecruits;


    @NotNull
    private String title;

    public PostDto toDto() {
        return new PostDto(
                content,
                wage,
                address,
                startTime,
                endTime,
                category,
                //numberOfApplicants,
                phoneNumber,
                //recruitmentStatus,
                wageType,
                recruitmentStartTime,
                recruitmentEndTime,
                lunchStartTime,
                lunchEndTime,
                numberOfRecruits,
                //postStatus
                title
        );
    }
}