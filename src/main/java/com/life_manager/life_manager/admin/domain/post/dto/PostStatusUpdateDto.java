package com.life_manager.life_manager.admin.domain.post.dto;

import com.life_manager.life_manager.admin.domain.post.Post;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostStatusUpdateDto {
    @NotNull
    private Post.RecruitmentStatus recruitmentStatus;
}
