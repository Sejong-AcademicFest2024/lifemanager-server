package com.life_manager.life_manager.user.domain.skill.service;

import java.util.List;
import java.util.stream.Collectors;

import com.life_manager.life_manager.user.domain.skill.dto.SkillCategoryResponse;
import com.life_manager.life_manager.user.domain.skill.dto.SkillResponse;
import com.life_manager.life_manager.user.domain.skill.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    @Transactional(readOnly = true)
    public List<SkillCategoryResponse> getSkillCategories() {
        return skillRepository.findAllCategories()
                .stream()
                .map(SkillCategoryResponse::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<SkillResponse> getSkillsByCategory(String category) {
        if (category == null || category.isBlank()) {
            return skillRepository.findAll()
                    .stream()
                    .map(SkillResponse::from)
                    .collect(Collectors.toUnmodifiableList());
        }

        return skillRepository.findByCategory(category)
                .stream()
                .map(SkillResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }
}