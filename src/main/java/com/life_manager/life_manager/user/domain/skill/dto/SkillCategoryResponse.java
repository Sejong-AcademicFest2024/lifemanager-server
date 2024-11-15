package com.life_manager.life_manager.user.domain.skill.dto;

import com.life_manager.life_manager.user.domain.skill.Skill;

public record SkillCategoryResponse(
        String category
) {
    public static SkillCategoryResponse from(Skill skill) {
        return new SkillCategoryResponse(skill.getCategory());
    }
}