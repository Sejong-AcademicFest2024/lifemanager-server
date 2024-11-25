package com.life_manager.life_manager.admin.domain.post.dto;

import com.life_manager.life_manager.admin.domain.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PostDto {
    private Integer id;
    private String content;
    private BigInteger wage;
    private String address;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String category;
    private Integer numberOfApplicants;
    private String phoneNumber;

    private Post.RecruitmentStatus recruitmentStatus;
    private Post.WageType wageType;
    private LocalDateTime recruitmentStartTime;
    private LocalDateTime recruitmentEndTime;
    @Schema(type = "String", pattern = "HH:mm")
    private LocalTime lunchStartTime;
    @Schema(type = "String", pattern = "HH:mm")
    private LocalTime lunchEndTime;
    private Integer numberOfRecruits;
    private Post.PostStatus postStatus;
    private String title;
    //private List<String> imageUrls;

    public PostDto(String content, BigInteger wage, String address, LocalDateTime startTime,
                   LocalDateTime endTime, String category, String phoneNumber,
                   Post.WageType wageType, LocalDateTime recruitmentStartTime,
                   LocalDateTime recruitmentEndTime, LocalTime lunchStartTime, LocalTime lunchEndTime, Integer numberOfRecruits, String title) {

        this.content = content;
        this.wage = wage;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.wageType = wageType;
        this.recruitmentStartTime = recruitmentStartTime;
        this.recruitmentEndTime = recruitmentEndTime;
        this.lunchStartTime = lunchStartTime;
        this.lunchEndTime = lunchEndTime;
        this.numberOfRecruits = numberOfRecruits;
        this.title = title;
    }

    public PostDto(String content, BigInteger wage, String address, LocalDateTime startTime, LocalDateTime endTime, String category, Integer numberOfApplicants, String phoneNumber, Post.WageType wageType, LocalDateTime recruitmentStartTime, LocalDateTime recruitmentEndTime, LocalTime lunchStartTime, LocalTime lunchEndTime, Integer numberOfRecruits, Post.PostStatus postStatus, String title) {

        this.content = content;
        this.wage = wage;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.numberOfApplicants = numberOfApplicants;
        this.phoneNumber = phoneNumber;
        this.wageType = wageType;
        this.recruitmentStartTime = recruitmentStartTime;
        this.recruitmentEndTime = recruitmentEndTime;
        this.lunchStartTime = lunchStartTime;
        this.lunchEndTime = lunchEndTime;
        this.numberOfRecruits = numberOfRecruits;
        this.postStatus = postStatus;
        this.title = title;
    }

    public static PostDto fromEntity(Post post) {
        return new PostDto(
                post.getId(),
                post.getContent(),
                post.getWage(),
                post.getAddress(),
                post.getStartTime(),
                post.getEndTime(),
                post.getCategory(),
                post.getNumberOfApplicants(),
                post.getPhoneNumber(),
                post.getRecruitmentStatus(),
                post.getWageType(),
                post.getRecruitmentStartTime(),
                post.getRecruitmentEndTime(),
                post.getLunchStartTime(),
                post.getLunchEndTime(),
                post.getNumberOfRecruits(),
                post.getPostStatus(),
                post.getTitle()
        );
    }
}