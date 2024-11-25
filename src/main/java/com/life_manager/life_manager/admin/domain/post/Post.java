package com.life_manager.life_manager.admin.domain.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.life_manager.life_manager.admin.Admin;
import com.life_manager.life_manager.admin.domain.post.dto.PostDto;
import com.life_manager.life_manager.admin.domain.post.dto.PostStatusUpdateDto;
import com.life_manager.life_manager.admin.domain.workspacePost.WorkspacePost;
import com.life_manager.life_manager.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private BigInteger wage;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Integer numberOfApplicants = 0;
    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false, name = "recruitment_status")
    @Enumerated(EnumType.STRING)
    private RecruitmentStatus recruitmentStatus = RecruitmentStatus.RECRUITING;
    @Column(nullable = false, name = "wage_type")
    @Enumerated(EnumType.STRING)
    private WageType wageType;
    @Column(nullable = false)
    private LocalDateTime recruitmentStartTime;
    @Column(nullable = false)
    private LocalDateTime recruitmentEndTime;
    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lunchStartTime;
    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lunchEndTime;
    @Column(nullable = false)
    private Integer numberOfRecruits;
    @Column(nullable = false, name = "post_status")
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus = PostStatus.PROCEEDING;


    public enum PostStatus {
        PROCEEDING, DONE
    }

    public enum RecruitmentStatus {
        RECRUITING, ENDED
    }

    public enum WageType {
        CASE, DAY, MONTH
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private Admin admin;
    @OneToMany(mappedBy = "post")
    private List<Employee> employeeList = new ArrayList<>();
    @OneToMany(mappedBy = "post")
    private List<WorkerApplicationStatus> workerApplicationStatusList = new ArrayList<>();
    @OneToMany(mappedBy = "post")
    private List<WorkspacePost> workspacePostList =new ArrayList<>();


    public Post(Admin admin, PostDto postDto) {
        this.admin = admin;
        admin.addPostList(this);
        this.content = postDto.getContent();
        this.wage = postDto.getWage();
        this.address = postDto.getAddress();
        this.startTime = postDto.getStartTime();
        this.endTime = postDto.getEndTime();
        this.category = postDto.getCategory();
        this.phoneNumber = postDto.getPhoneNumber();
        this.recruitmentStatus = RecruitmentStatus.RECRUITING;
        this.wageType = postDto.getWageType();
        this.recruitmentStartTime = postDto.getRecruitmentStartTime();
        this.recruitmentEndTime = postDto.getRecruitmentEndTime();
        this.lunchStartTime = postDto.getLunchStartTime();
        this.lunchEndTime = postDto.getLunchEndTime();
        this.numberOfRecruits = postDto.getNumberOfRecruits();
        this.postStatus = PostStatus.PROCEEDING;
        this.title = postDto.getTitle();
    }

    public void updateByDto(PostDto postDto) {
        this.content = postDto.getContent();
        this.wage = postDto.getWage();
        this.address = postDto.getAddress();
        this.startTime = postDto.getStartTime();
        this.endTime = postDto.getEndTime();
        this.category = postDto.getCategory();
        this.numberOfApplicants = postDto.getNumberOfApplicants();
        this.phoneNumber = postDto.getPhoneNumber();
        this.recruitmentStatus = postDto.getRecruitmentStatus();
        this.wageType = postDto.getWageType();
        this.recruitmentStartTime = postDto.getRecruitmentStartTime();
        this.recruitmentEndTime = postDto.getRecruitmentEndTime();
        this.lunchStartTime = postDto.getLunchStartTime();
        this.lunchEndTime = postDto.getLunchEndTime();
        this.numberOfRecruits = postDto.getNumberOfRecruits();
        this.postStatus = postDto.getPostStatus();
        this.title = postDto.getTitle();
    }

    public void addWorkerApplicationStatus(WorkerApplicationStatus workerApplicationStatus) {
        workerApplicationStatusList.add(workerApplicationStatus);
        numberOfApplicants++;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public boolean isAdmin(Admin admin) {
        return this.admin.equals(admin);
    }

    public void updateStatusByDto(PostStatusUpdateDto postStatusUpdateDto) {
        this.recruitmentStatus = postStatusUpdateDto.getRecruitmentStatus();
    }

    public void addWorkspacePost(WorkspacePost workspacePost) {
        this.workspacePostList.add(workspacePost);
        workspacePost.addPost(this);
    }
}