package com.life_manager.life_manager.user.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;

    private String title;

    private BigDecimal wage;

    @Column(columnDefinition = "VARCHAR(255)")
    private String address;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Column(columnDefinition = "VARCHAR(255)")
    private String category;

    private Integer numberOfApplicants;

    @Column(columnDefinition = "CHAR(11)")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private RecruitmentStatus recruitmentStatus;

    @Column(columnDefinition = "CHAR(36)", nullable = false)
    private String adminId;

    @Enumerated(EnumType.STRING)
    private WageType wageType;

    private LocalDateTime recruitmentStartTime;

    private LocalDateTime recruitmentEndTime;

    private Time lunchStartTime;

    private Time lunchEndTime;

    private Integer numberOfRecruits;

    private LocalDateTime createdAt;

    public boolean isClosed() {
        if (recruitmentStatus == RecruitmentStatus.ENDED) {
            return true;
        }
        return LocalDateTime.now().isBefore(recruitmentStartTime) || LocalDateTime.now().isAfter(recruitmentEndTime);
    }
}