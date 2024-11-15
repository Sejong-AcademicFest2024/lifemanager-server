package com.life_manager.life_manager.user.domain.worker.relation;

import com.life_manager.life_manager.user.domain.post.Post;
import com.life_manager.life_manager.user.domain.worker.Worker;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkerApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status", nullable = false)
    private ApplicationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Builder(access = AccessLevel.PRIVATE)
    private WorkerApplicationStatus(ApplicationStatus status, Worker worker, Post post) {
        this.status = status;
        this.worker = worker;
        this.post = post;
    }

    public static WorkerApplicationStatus apply(Worker worker, Post post) {
        return WorkerApplicationStatus.builder()
                .status(ApplicationStatus.PENDING)
                .worker(worker)
                .post(post)
                .build();
    }

    public boolean isApplied(Post post) {
        return this.post.equals(post)
                && (this.status == ApplicationStatus.PENDING || this.status == ApplicationStatus.ACCEPTED);
    }

    public void updateStatus(ApplicationStatus status) {
        this.status = status;
    }
}