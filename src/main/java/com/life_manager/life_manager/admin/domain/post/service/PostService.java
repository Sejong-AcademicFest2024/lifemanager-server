package com.life_manager.life_manager.admin.domain.post.service;

import com.life_manager.life_manager.admin.Admin;
import com.life_manager.life_manager.admin.domain.admin.repository.AdminRepository;
import com.life_manager.life_manager.admin.domain.post.Post;
import com.life_manager.life_manager.admin.domain.post.dto.PostDto;
import com.life_manager.life_manager.admin.domain.post.repository.PostRepository;
import com.life_manager.life_manager.admin.domain.workspacePost.WorkspacePost;
import com.life_manager.life_manager.global.error.ErrorCode;
import com.life_manager.life_manager.global.exception.CustomException;
import com.life_manager.life_manager.user.infra.S3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final S3Service s3Service;
    private final PostRepository postRepository;
    private final AdminRepository adminRepository;
    private final WorkspacePostRepository workspacePostRepository;
    @Transactional
    public PostDto createPost(String adminId, PostDto postDto, List<MultipartFile> images){
        Admin admin = getAdminById(adminId);
        Post post = new Post(admin, postDto);
        postRepository.save(post);

        List<String> imageUrls = s3Service.uploadImageList(adminId, images);
        for (String imageUrl : imageUrls) {
            WorkspacePost workspacePost = new WorkspacePost(imageUrl);
            post.addWorkspacePost(workspacePost);
            workspacePostRepository.save(workspacePost);
        }
        return PostDto.fromEntity(post);
    }

    @Transactional
    public PostDto updatePost(String adminId, Integer postId, PostDto postDto) {
        Admin admin = getAdminById(adminId);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND)
        );
        if(!post.isAdmin(admin)){
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        post.updateByDto(postDto);
        return PostDto.fromEntity(post);
    }

    public List<PostDto> getAllPosts(String adminId) {
        Admin admin = getAdminById(adminId);
        return admin.getPostList().stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .map(PostDto::fromEntity)
                .toList();
    }
    public PostDto getPost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND)
        );
        return PostDto.fromEntity(post);
    }
    private Admin getAdminById(String adminId){
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND));
        return admin;
    }

    public void deletePost(String adminId, Integer postId) {
        Admin admin = getAdminById(adminId);
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));
        if(!post.isAdmin(admin)){
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        postRepository.delete(post);
    }
}
