package com.life_manager.life_manager.admin.domain.workerApplicationStatus;

import com.life_manager.life_manager.admin.domain.post.Post;
import com.life_manager.life_manager.admin.domain.worker.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WorkerApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;

    @Column(nullable = false, name = "application_status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus = ApplicationStatus.PENDING;

    @JoinColumn(name="post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn(name="worker_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;

    public enum ApplicationStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        EXPIRED,
        CANCEL
    }



    public WorkerApplicationStatus(Post post, Worker worker) {
        this.applicationStatus = ApplicationStatus.PENDING;
        this.post = post;
        post.addWorkerApplicationStatus(this);
        this.worker = worker;
        worker.addWorkerApplicationStatus(this);
    }

    public void updateByDto(WorkerApplicationStatusUpdateDto requestDto) {
        this.applicationStatus = requestDto.getApplicationStatus();
    }
}