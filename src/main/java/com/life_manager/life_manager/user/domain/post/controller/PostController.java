package com.life_manager.life_manager.user.domain.post.controller;

import java.util.List;

import com.life_manager.life_manager.user.domain.post.dto.PostDetailResponse;
import com.life_manager.life_manager.user.domain.post.dto.PostPreviewResponse;
import com.life_manager.life_manager.user.domain.post.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회합니다.")
    @GetMapping
    public List<PostPreviewResponse> getPostPreviews(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String category) {
        return postService.getPostPreviews(page, size, category);
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 상세를 조회합니다.")
    @GetMapping("/{id}")
    public PostDetailResponse getPostDetail(@PathVariable Long id) {
        return postService.getPostDetail(id);
    }

    @Operation(summary = "게시글 지원", description = "게시글에 지원합니다.")
    @PostMapping("/{postId}/apply")
    public void applyPost(@PathVariable Long postId) {
        postService.applyPost(postId);
    }
}
