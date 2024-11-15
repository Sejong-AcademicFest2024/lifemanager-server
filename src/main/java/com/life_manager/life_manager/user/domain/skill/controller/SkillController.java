package com.life_manager.life_manager.user.domain.skill.controller;

import java.util.List;

import com.life_manager.life_manager.user.domain.skill.dto.SkillCategoryResponse;
import com.life_manager.life_manager.user.domain.skill.dto.SkillResponse;
import com.life_manager.life_manager.user.domain.skill.service.SkillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skill")
public class SkillController {
    private final SkillService skillService;

    @Operation(summary = "스킬 카테고리 조회", description = "스킬 카테고리를 조회합니다.")
    @GetMapping("/category")
    public List<SkillCategoryResponse> getSkillCategories() {
        return skillService.getSkillCategories();
    }

    @Operation(summary = "카테고리의 스킬 조회", description = "선택한 카테고리의 스킬을 조회합니다. 카테고리 미지정시 모든 스킬을 조회합니다.")
    @GetMapping
    public List<SkillResponse> getSkillsByCategory(@RequestParam(required = false) String category) {
        return skillService.getSkillsByCategory(category);
    }
}