package com.life_manager.life_manager.user.domain.resume.service;

import java.util.List;
import java.util.stream.Collectors;

import com.life_manager.life_manager.user.domain.certificate.Certificate;
import com.life_manager.life_manager.user.domain.certificate.repostiroy.CertificateRepository;
import com.life_manager.life_manager.user.domain.resume.Career;
import com.life_manager.life_manager.user.domain.resume.Resume;
import com.life_manager.life_manager.user.domain.resume.dto.*;
import com.life_manager.life_manager.user.domain.resume.repository.ResumeRepository;
import com.life_manager.life_manager.user.domain.skill.Skill;
import com.life_manager.life_manager.user.domain.skill.repository.SkillRepository;
import com.life_manager.life_manager.user.infra.S3.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeService {
    private final S3Service s3Service;

    private final ResumeRepository resumeRepository;
    private final SkillRepository skillRepository;

    private final CertificateRepository certificateRepository;

    public void updateCertificates(CertificateUpdateRequest request, String workerId) {
        Resume resume = resumeRepository.findById(workerId)
                .orElseThrow();
        List<Certificate> certificates = certificateRepository.findAllById(request.ids());

        resume.updateCertificates(certificates);

        resumeRepository.save(resume);
    }

    public void updateStatement(StatementUpdateRequest request, String workerId) {
        Resume resume = resumeRepository.findById(workerId)
                .orElseThrow();

        resume.updateStatement(request.statement());

        resumeRepository.save(resume);
    }

    public void updateCareer(List<CareerUpdateRequest> requests, String workerId) {
        Resume resume = resumeRepository.findById(workerId)
                .orElseThrow();

        List<Career> careers = requests.stream()
                .map(request -> Career.create(resume, request))
                .collect(Collectors.toUnmodifiableList());

        resume.updateCareers(careers);

        resumeRepository.save(resume);
    }

    @SneakyThrows
    public void uploadProfileImage(String workerId, MultipartFile profileImage) {
        Resume resume = resumeRepository.findById(workerId)
                .orElseThrow();

        String uploadedUrl = s3Service.uploadProfileImage(workerId, profileImage);

        resume.updateProfileImageUrl(uploadedUrl);
    }

    public ProfileImageUrlResponse getProfileImageUploadedUrl(String workerId) {
        Resume resume = resumeRepository.findById(workerId)
                .orElseThrow();

        return new ProfileImageUrlResponse(resume.getUploadedUrl());
    }

    public ResumeInfoResponse getResumeInfo(String workerId) {
        Resume resume = resumeRepository.findById(workerId)
                .orElseThrow();

        return ResumeInfoResponse.of(resume);
    }

    public void updateSkills(List<Integer> skillIds, String workerId) {
        Resume resume = resumeRepository.findById(workerId)
                .orElseThrow();

        Skill[] skills = skillIds.stream()
                .map(skillId -> skillRepository.findById(skillId)
                        .orElseThrow())
                .toArray(Skill[]::new);

        resume.addSkills(skills);
        resumeRepository.save(resume);
    }
}
