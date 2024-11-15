package com.life_manager.life_manager.user.domain.resume;

import com.life_manager.life_manager.user.domain.certificate.Certificate;
import com.life_manager.life_manager.user.domain.skill.Skill;
import com.life_manager.life_manager.user.domain.worker.Worker;
import com.life_manager.life_manager.user.domain.worker.relation.ResumeSkillRelation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ResumeSkillRelation> resumeSkillRelations = new ArrayList<>();
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ResumeCertificateRelation> resumeCertificateRelations = new ArrayList<>();
    @Id
    private String id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Worker worker;
    private String uploadedUrl;
    private String statement;
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Career> careers;

    private Resume(Worker worker) {
        this.worker = worker;
    }

    public static Resume create(Worker worker) {
        return new Resume(worker);
    }

    public void updateCertificates(List<Certificate> certificates) {
        resumeCertificateRelations.clear();
        for (Certificate certificate : certificates) {
            resumeCertificateRelations.add(ResumeCertificateRelation.create(this, certificate));
        }
    }

    public void updateStatement(String statement) {
        this.statement = statement;
    }

    public void updateCareers(List<Career> careers) {
        this.careers.clear();
        this.careers.addAll(careers);
    }

    public void updateProfileImageUrl(String uploadedUrl) {
        this.uploadedUrl = uploadedUrl;
    }

    public void addSkills(Skill... skills) {
        this.resumeSkillRelations.clear();

        for (Skill skill : skills) {
            resumeSkillRelations.add(ResumeSkillRelation.create(this, skill));
        }
    }
}
