package com.life_manager.life_manager.admin.domain.workspacePost;

import com.life_manager.life_manager.admin.domain.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WorkspacePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;


    public WorkspacePost(String imageUrl) {
        this.url = imageUrl;
    }

    public void addPost(Post post) {
        this.post = post;
    }
}