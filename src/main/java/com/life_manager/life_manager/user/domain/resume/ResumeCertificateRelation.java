package com.life_manager.life_manager.user.domain.resume;

import com.life_manager.life_manager.user.domain.certificate.Certificate;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeCertificateRelation {
    @EmbeddedId
    private ResumeCertificateRelationId resumeCertificateRelationId;

    @MapsId("workerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", columnDefinition = "char(36)")
    private Resume resume;

    @MapsId("certificateId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id", columnDefinition = "SMALLINT UNSIGNED")
    private Certificate certificate;

    public static ResumeCertificateRelation create(Resume resume, Certificate certificate) {
        ResumeCertificateRelation resumeCertificateRelation = new ResumeCertificateRelation();

        resumeCertificateRelation.resumeCertificateRelationId = new ResumeCertificateRelationId();
        resumeCertificateRelation.resumeCertificateRelationId.workerId = resume.getId();
        resumeCertificateRelation.resumeCertificateRelationId.certificateId = certificate.getId();

        resumeCertificateRelation.resume = resume;
        resumeCertificateRelation.certificate = certificate;

        return resumeCertificateRelation;
    }

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @EqualsAndHashCode
    public static class ResumeCertificateRelationId implements Serializable {
        private String workerId;
        private Integer certificateId;
    }
}
