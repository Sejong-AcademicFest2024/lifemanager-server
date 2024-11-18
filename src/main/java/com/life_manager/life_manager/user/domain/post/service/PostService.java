package com.life_manager.life_manager.user.domain.post.service;

import com.life_manager.life_manager.global.error.ErrorCode;
import com.life_manager.life_manager.global.exception.CustomException;
import com.life_manager.life_manager.global.security.util.SecurityContextUtil;
import com.life_manager.life_manager.user.domain.post.Post;
import com.life_manager.life_manager.user.domain.post.dto.PostDetailResponse;
import com.life_manager.life_manager.user.domain.post.dto.PostPreviewResponse;
import com.life_manager.life_manager.user.domain.post.repository.PostRepository;
import com.life_manager.life_manager.user.domain.worker.Worker;
import com.life_manager.life_manager.user.domain.worker.relation.ApplicationStatus;
import com.life_manager.life_manager.user.domain.worker.relation.WorkerApplicationStatus;
import com.life_manager.life_manager.user.domain.worker.relation.repository.WorkerApplicationStatusRepository;
import com.life_manager.life_manager.user.domain.worker.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final WorkerRepository workerRepository;
    private final WorkerApplicationStatusRepository workerApplicationStatusRepository;

    @Transactional(readOnly = true)
    public List<PostPreviewResponse> getPostPreviews(int page, int size, String category) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        if (category == null || category.isBlank()) {
            return postRepository.findAll(pageable)
                    .stream()
                    .filter(post -> !post.isClosed())
                    .map(PostPreviewResponse::from)
                    .collect(Collectors.toUnmodifiableList());
        }

        return postRepository.findAllByCategory(category, pageable)
                .stream()
                .filter(post -> !post.isClosed())
                .map(PostPreviewResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPostDetail(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow();

        // 모집된 인원
        List<WorkerApplicationStatus> workerApplicationStatuses = workerApplicationStatusRepository.findAllByPost(post);
        int acceptedCount = (int)workerApplicationStatuses.stream()
                .filter(workerApplicationStatus -> workerApplicationStatus.getStatus() == ApplicationStatus.ACCEPTED)
                .count();

        return PostDetailResponse.from(post, acceptedCount);
    }

    public void applyPost(Long postId) {
        String workerId = SecurityContextUtil.extractWorkerId();
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        validateApply(worker, post);

        worker.apply(post);
        workerRepository.save(worker);
    }

    private void validateApply(Worker worker, Post post) {
        if (worker.isApplied(post)) {
            throw new CustomException(ErrorCode.ALREADY_APPLIED);
        }
        if (post.isClosed()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }
    }
}