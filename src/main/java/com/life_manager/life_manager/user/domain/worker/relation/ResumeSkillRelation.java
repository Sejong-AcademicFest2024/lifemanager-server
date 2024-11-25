package com.life_manager.life_manager.user.domain.worker.relation;

import com.life_manager.life_manager.user.domain.resume.Resume;
import com.life_manager.life_manager.user.domain.skill.Skill;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeSkillRelation {
    @EmbeddedId
    private ResumeSkillRelationId resumeSkillRelationId;

    @MapsId("workerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", columnDefinition = "char(36)")
    private Resume resume;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", columnDefinition = "SMALLINT UNSIGNED")
    private Skill skill;

    public static ResumeSkillRelation create(Resume resume, Skill skill) {
        ResumeSkillRelation resumeSkillRelation = new ResumeSkillRelation();

        resumeSkillRelation.resumeSkillRelationId = new ResumeSkillRelationId();
        resumeSkillRelation.resumeSkillRelationId.workerId = resume.getId();
        resumeSkillRelation.resumeSkillRelationId.skillId = skill.getId();

        resumeSkillRelation.resume = resume;
        resumeSkillRelation.skill = skill;

        return resumeSkillRelation;
    }

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class ResumeSkillRelationId implements Serializable {
        private String workerId;
        private Integer skillId;
    }
}