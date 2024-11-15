package com.life_manager.life_manager.user.domain.resume;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    private String companyName;
    private String role;
    private String roleDetail;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @Builder(access = AccessLevel.PRIVATE)
    private Career(Resume resume, String companyName, String role, String roleDetail, LocalDate startDate,
                   LocalDate endDate) {
        this.resume = resume;
        this.companyName = companyName;
        this.role = role;
        this.roleDetail = roleDetail;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Career create(Resume resume, CareerUpdateRequest request) {
        return Career.builder()
                .resume(resume)
                .companyName(request.companyName())
                .role(request.role())
                .roleDetail(request.roleDetail())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
    }
}
