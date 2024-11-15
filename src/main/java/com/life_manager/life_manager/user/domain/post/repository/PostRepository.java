package com.life_manager.life_manager.user.domain.post.repository;

import java.util.List;

import com.life_manager.life_manager.user.domain.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCategory(String category, Pageable pageable);
}
