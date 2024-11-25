package com.life_manager.life_manager.admin.domain.post.repository;

import com.life_manager.life_manager.admin.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
