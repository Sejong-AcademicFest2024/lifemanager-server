package com.life_manager.life_manager.user.domain.skill.repository;


import com.life_manager.life_manager.user.domain.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    @Query("SELECT DISTINCT s.category FROM Skill s")
    List<String> findAllCategories();

    List<Skill> findByCategory(String category);
}
