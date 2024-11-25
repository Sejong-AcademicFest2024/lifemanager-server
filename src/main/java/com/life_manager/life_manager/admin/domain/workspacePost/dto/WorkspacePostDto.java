package com.life_manager.life_manager.admin.domain.workspacePost.dto;

import com.life_manager.life_manager.admin.domain.workspacePost.WorkspacePost;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkspacePostDto {
    private String url;

    public static WorkspacePostDto fromEntity(WorkspacePost workspacePost) {
        return new WorkspacePostDto((workspacePost.getUrl()));
    }
}
